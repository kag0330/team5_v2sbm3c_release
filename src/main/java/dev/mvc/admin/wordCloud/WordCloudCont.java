package dev.mvc.admin.wordCloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/wordcloud")
public class WordCloudCont {
  @GetMapping("index")
  public String index() {
    return "admin/wordcloud/index";
  }
}
