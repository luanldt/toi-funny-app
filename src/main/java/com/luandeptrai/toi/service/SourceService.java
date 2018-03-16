package com.luandeptrai.toi.service;


import org.jsoup.select.Elements;

import java.io.IOException;

public interface SourceService {
  public Elements htmlSource(int page, String source) throws IOException;
}
