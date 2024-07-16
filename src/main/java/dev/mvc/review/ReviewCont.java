package dev.mvc.review;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.likes.LikesProcInter;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.option.OptionProcInter;
import dev.mvc.shoes.ShoesAllVO;
import dev.mvc.shoes.ShoesProcInter;
import dev.mvc.team5.DefaultCont;
import dev.mvc.tool.Alert;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/review")
@Controller
public class ReviewCont {
  @Autowired
  @Qualifier("dev.mvc.shoes.ShoesProc")
  private ShoesProcInter shoesProc;

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
  @Qualifier("dev.mvc.likes.LikesProc")
  private LikesProcInter likesProc;

  public ReviewCont() {
    System.out.println("-> ShoesCont created.");
  }

  public int record_per_page = 5;
  public int page_per_block = 5;

  /* 후기 작성 */
  @PostMapping(value = "/create")
  @ResponseBody
  public Map<String, Object> create(HttpSession session, Model model, @RequestBody Map<String, String> map) {

    // session memberno 추가
    // int memberno = session.getMemberno();

    ReviewVO reviewVO = new ReviewVO();
    reviewVO.setContents(map.get("contents"));
    reviewVO.setRating(Double.valueOf(map.get("rating")));
    reviewVO.setShoesno(Integer.parseInt(map.get("shoesno")));
    reviewVO.setMemberno(1);

    int cnt = this.reviewProc.create(reviewVO);
    Map<String, Object> response = new HashMap<>();
    if (cnt == 1) {
      ArrayList<ShoesAllVO> review = this.reviewProc.review_list(Integer.parseInt(map.get("shoesno")));
      response.put("review", review);
      response.put("success", true);
    }
    return response;
  }

  /**
   * * 전체 후기
   * 
   * @param session
   * @param model
   * @param shoesno
   * @param categoryno
   * @return
   */
  @GetMapping(value = "/list/{shoesno}")
  public String list(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    MemberVO memberVO = (MemberVO)session.getAttribute("login");
    if(memberVO != null) {
      model.addAttribute("memberno", memberVO.getMemberno());
      model.addAttribute("nickname", memberVO.getNickname());
    }
    
    ArrayList<ShoesAllVO> review = this.reviewProc.list_search_paging(shoesno, word, now_page, this.record_per_page);
    if (review.size() == 0) {
      model.addAttribute("no_review", true);
    }else {
      model.addAttribute("review", review);
      
      int search_count = this.reviewProc.list_search_count(shoesno, word);
      String paging = this.reviewProc.pagingBox(now_page, "", "/review/list/"+shoesno, search_count, this.record_per_page,
          this.page_per_block);

      int no = search_count - ((now_page - 1) * this.record_per_page);

      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      model.addAttribute("no", no);
    }
    return "review/list";
  }

  @PostMapping(value = "update")
  @ResponseBody
  public Map<String, Object> update(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    Map<String, Object> response = new HashMap<>();

    ReviewVO reviewVO = new ReviewVO();
    reviewVO.setContents(map.get("contents"));
    reviewVO.setRating(Double.valueOf(map.get("rating")));
    reviewVO.setReviewno(Integer.parseInt(map.get("reviewno")));

    int cnt = this.reviewProc.update(reviewVO);
    if (cnt == 1) {
      response.put("success", true);
    }

    return response;
  }

  @PostMapping(value = "delete")
  @ResponseBody
  public Map<String, Object> delete(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    Map<String, Object> response = new HashMap<>();
    int reviewno = Integer.parseInt(map.get("reviewno"));
    System.out.println("review" + reviewno);

    this.likesProc.delete(reviewno);
    this.reviewProc.delete(reviewno);

    response.put("success", true);
    return response;
  }

  @GetMapping("/myReview")
  public String myReport(HttpSession session, Model model,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    if (memberVO != null) {
      int memberno = memberVO.getMemberno();
      ArrayList<ShoesAllVO> list = this.reviewProc.myReview(memberno);
      model.addAttribute("list", list);

      int search_count = this.reviewProc.myReviewCount(memberno);
      String paging = this.reviewProc.pagingBox(now_page, "", "/review/myreview", search_count, this.record_per_page,
          this.page_per_block);

      int no = search_count - ((now_page - 1) * this.record_per_page);

      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      model.addAttribute("no", no);
      return "review/myReview";
    } else {
      Alert message = new Alert("로그인 후 이용해주세요.", "/login/signin", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
  }
}