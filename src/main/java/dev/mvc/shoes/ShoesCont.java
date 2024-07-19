package dev.mvc.shoes;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.category.CategoryProcInter;
import dev.mvc.category.CategoryVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.option.OptionProcInter;
import dev.mvc.option.OptionVO;

import dev.mvc.paymentTotal.PaymentTotalProcInter;

import dev.mvc.reportType.ReportTypeProcInter;
import dev.mvc.reportType.ReportTypeVO;
import dev.mvc.review.ReviewProcInter;
import dev.mvc.shoesFile.ShoesFileProc;
import dev.mvc.shoesFile.ShoesFileProcInter;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/shoes")
@Controller
public class ShoesCont {
  @Autowired
  @Qualifier("dev.mvc.shoes.ShoesProc")
  private ShoesProcInter shoesProc;

  @Autowired
  @Qualifier("dev.mvc.category.CategoryProc")
  private CategoryProcInter categoryProc;

  @Autowired
  @Qualifier("dev.mvc.option.OptionProc")
  private OptionProcInter optionProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc;

  @Autowired
  @Qualifier("dev.mvc.reportType.ReportTypeProc")
  private ReportTypeProcInter reportTypeProc;
  
  @Autowired
  @Qualifier("dev.mvc.paymentTotal.PaymentTotalProc")
  private PaymentTotalProcInter paymentTotalProc;
  
  @Autowired
  @Qualifier("dev.mvc.shoesFile.ShoesFileProc")
  private ShoesFileProcInter shoesFileProc;

  public int record_per_page = 5;
  public int page_per_block = 5;

  public ShoesCont() {
    System.out.println("-> ShoesCont created.");
  }

  private void table_paging(Model model, int categoryno, String word, int now_page) {

    ArrayList<ShoesVO> list = this.shoesProc.list_search_paging(categoryno, word);
    model.addAttribute("list", list);

    int search_count = this.shoesProc.list_search_count(categoryno, word);

    int no = search_count - ((now_page - 1) * this.record_per_page);

    model.addAttribute("now_page", now_page);
    model.addAttribute("categoryno", categoryno);
    model.addAttribute("word", word);
    model.addAttribute("no", no);
  }

  /**
   * 신발 리스트
   * 
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/list")
  public String list(HttpSession session, Model model,
      @RequestParam(name = "categoryno", defaultValue = "0", required = false) Integer categoryno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    if (categoryno != 0) {
      CategoryVO categoryVO = categoryProc.category_select(categoryno);
      model.addAttribute("categoryVO", categoryVO);
    }

    CategoryVO categoryVO = categoryProc.category_select(categoryno);
    model.addAttribute("categoryVO", categoryVO);
    
    model.addAttribute("shoesFileList", this.shoesFileProc.list());
    
    
    table_paging(model, categoryno, word, now_page);

    return "shoes/list";
  }

  /**
   * sale 신발 리스트
   * 
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/sale_list")
  public String sale_list(HttpSession session, Model model,
      @RequestParam(name = "categoryno", defaultValue = "0", required = false) Integer categoryno) {

    ArrayList<ShoesVO> list = shoesProc.Shoes_discount();
    model.addAttribute("list", list);
    
    model.addAttribute("shoesFileList", this.shoesFileProc.list());

    return "shoes/list";
  }

  /**
   * 남성 리스트
   * 
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/man_list")
  public String man_list(HttpSession session, Model model,
      @RequestParam(name = "categoryno", defaultValue = "0", required = false) Integer categoryno) {

    ArrayList<CategoryVO> list = shoesProc.Shoes_man(categoryno);
    model.addAttribute("list", list);

    return "shoes/list";
  }

  /**
   * sale 여성 리스트
   * 
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/girl_list")
  public String girl_list(HttpSession session, Model model,
      @RequestParam(name = "categoryno", defaultValue = "0", required = false) Integer categoryno) {

    ArrayList<CategoryVO> list = shoesProc.Shoes_girl(categoryno);
    model.addAttribute("list", list);

    return "shoes/list";
  }

  /**
   * 제품 상세
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/{shoesno}")
  public String details(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "categoryno", defaultValue = "0", required = false) int categoryno) {
    // session에 들어있는 로그인 값
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    if (memberVO != null) {
      model.addAttribute("memberno", memberVO.getMemberno());
      model.addAttribute("nickname", memberVO.getNickname());
    }
    
    ShoesAllVO shoesAllVO = this.shoesProc.read(shoesno);
    model.addAttribute("shoesAllVO", shoesAllVO);
    
//    ArrayList<Integer> sizes = this.optionProc.option_sizes(shoesno);
//    model.addAttribute("sizes", sizes);
//    ArrayList<String> color = this.optionProc.option_color(shoesno);
//    model.addAttribute("color", color);
    
    System.out.println(shoesAllVO.toString());
    
    ArrayList<ShoesAllVO> review = this.reviewProc.review_list(shoesno);
    
    model.addAttribute("rating", this.reviewProc.review_avg(shoesno));
    if (review.size() == 0) {
      model.addAttribute("no_review", true);
    }
    
    model.addAttribute("review", review);
    ArrayList<ReportTypeVO> reportType = this.reportTypeProc.search_type();
    model.addAttribute("reportType", reportType); 
    
    //kag0330 추가
    model.addAttribute("options", this.optionProc.optionByshoesno(shoesno));

    return "shoes/detail"; // /templates/shoes/read.html
  }

  
  /**
   * 이용안내
   * 
   * @param 
   * @param 
   * @return
   */
  @GetMapping(value = "/guide")
  public String guide(HttpSession session, Model model) {


    return "shoes/guide";
  }

  @ResponseBody
  @PostMapping("{shoesno}/payment")
  public boolean shoespayment(@PathVariable("shoesno")int shoesno, @RequestBody Map<String, Object> map) {
    if(!this.paymentTotalProc.create(map))
      return false;
    return true;
  }

}
