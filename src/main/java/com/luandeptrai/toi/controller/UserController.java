package com.luandeptrai.toi.controller;

import com.luandeptrai.toi.models.UserEntity;
import com.luandeptrai.toi.service.UserServices;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserServices userServices;

  @RequestMapping(value = {"/"}, method = RequestMethod.GET)
  public ModelAndView user() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("user");
    modelAndView.addObject("user", new UserEntity());
    return modelAndView;
  }

  @RequestMapping(value = {"/"}, method = RequestMethod.POST)
  public ModelAndView addUser(@Valid @ModelAttribute("user") UserEntity entity,
                              BindingResult result) {
    ModelAndView modelAndView = new ModelAndView();
    if (result.hasErrors()) {
      modelAndView.setViewName("error");
    }
    userServices.save(entity);
    return modelAndView;
  }
}
