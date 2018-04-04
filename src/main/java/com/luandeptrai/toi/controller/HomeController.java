package com.luandeptrai.toi.controller;

import com.luandeptrai.toi.service.SourceService;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/funny")
public class HomeController {

  @Autowired
  private SourceService sourceService;

  @RequestMapping(value={"/{page}"}, method = RequestMethod.GET)
  public ModelAndView login(@PathVariable("page") int page) throws IOException {
    ModelAndView modelAndView = new ModelAndView();

    Elements elements = sourceService.htmlSource(page > 0 ? page : 1, "http://xem.vn/new/");
    Map<String, String> map = new HashMap<>();
    elements.forEach(t -> {
      t.select("div.stats").remove();
      t.select("div.uploader").remove();
      t.select("[id^=div-gpt-ad]").parents().remove();
      String id = t.attr("data-id");
      map.put(id, t.html());
    });
    modelAndView.addObject("value", map);
    modelAndView.addObject("page", page+1);
    modelAndView.setViewName("home");
    return modelAndView;
  }

}
