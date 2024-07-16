package dev.mvc.option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.member.MemberProcInter;
import dev.mvc.review.ReviewProcInter;
import dev.mvc.shoes.ShoesProcInter;

@RequestMapping("/options")
@Controller
public class OptionCont {
  @Autowired
  @Qualifier("dev.mvc.shoes.ShoesProc")
  private ShoesProcInter shoesProc;

  @Autowired
  @Qualifier("dev.mvc.option.OptionProc")
  private OptionProcInter optionProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") // @Service("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 5;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 5;

  public OptionCont() {
    System.out.println("-> ShoesCont created.");
  }
  
  @PostMapping(value="option_color")
  @ResponseBody
  public Map<String, Object> option_color(@RequestBody Map<String, Object> map) {
    int shoesno = (Integer) map.get("shoesno");
    int sizes = (Integer) map.get("sizes");
    
    ArrayList<OptionVO> coloramount = this.optionProc.option_coloramount(shoesno, sizes);
    Map<String, Object> response = new HashMap<>();
    response.put("coloramount", coloramount);
    response.put("success", true);
    return response;
  }

  
}
