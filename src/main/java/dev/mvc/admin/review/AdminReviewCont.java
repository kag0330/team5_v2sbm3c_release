package dev.mvc.admin.review;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.review.ReviewProcInter;
import dev.mvc.shoes.ShoesAllVO;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/admin/review")
@Controller
public class AdminReviewCont {

  @Autowired
  @Qualifier("dev.mvc.review.ReviewProc")
  private ReviewProcInter reviewProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 5;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 5;

  public AdminReviewCont() {
    System.out.println("-> AdminReviewCont created.");
  }

  /** 리뷰 페이징 */
  private void review_table_paging(Model model, int shoesno, String word, int now_page) {
    ArrayList<ShoesAllVO> list = this.reviewProc.list_search_paging(shoesno, word, now_page, this.record_per_page);
    model.addAttribute("list", list);
    
    int search_count = this.reviewProc.list_search_count(shoesno, word);
    String paging = this.reviewProc.pagingBox(now_page, word, "/admin/review/list/"+shoesno, search_count, this.record_per_page,
        this.page_per_block);

    int no = search_count - ((now_page - 1) * this.record_per_page);

    model.addAttribute("paging", paging);
    model.addAttribute("shoesno", shoesno);
    model.addAttribute("now_page", now_page);
    model.addAttribute("word", word);
    model.addAttribute("no", no);
  }


  /** 리뷰 목록 */
  @GetMapping(value = "/list/{shoesno}")
  public String list(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    // word = Tool.checkNull(word).trim();

    review_table_paging(model, shoesno, word, now_page);
    return "admin/review/list";
  }
  
  /** 리뷰 삭제 */
  @PostMapping(value = "/delete")
  @ResponseBody
  public Map<String, Object> admin_delete(@RequestBody Map<String, Object> map) {

    Map<String, Object> response = new HashMap<>();
    int reviewno = (Integer) map.get("reviewno");

    int result = this.reviewProc.delete(reviewno);

    if (result == 1) {
      response.put("success", true);
    } else {
      response.put("message", "장바구니 제품 삭제에 실패했습니다.");
    }

    return response;
  }
}
