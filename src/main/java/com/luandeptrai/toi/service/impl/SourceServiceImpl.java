package com.luandeptrai.toi.service.impl;

import com.luandeptrai.toi.service.SourceService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SourceServiceImpl implements SourceService {
  // Thanks to xem.vn
  @Override
  public Elements htmlSource(int page, String source) throws IOException {
    Elements documentResult = null;
    Document document = Jsoup.connect(source + page).get();
    if(document != null) {
      documentResult = document.select("div.photoListItem");
    }
    return documentResult;
  }
}
