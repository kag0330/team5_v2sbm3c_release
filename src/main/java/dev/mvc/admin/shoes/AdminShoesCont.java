package dev.mvc.admin.shoes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import dev.mvc.review.ReviewProcInter;
import dev.mvc.shoes.ShoesProcInter;
import dev.mvc.shoes.ShoesVO;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/admin/shoes")
@Controller
public class AdminShoesCont {
  @Autowired
  @Qualifier("dev.mvc.shoes.ShoesProc")
  private ShoesProcInter shoesProc;

  @Autowired
  @Qualifier("dev.mvc.option.OptionProc")
  private OptionProcInter optionProc;

  @Autowired
  @Qualifier("dev.mvc.category.CategoryProc")
  private CategoryProcInter categoryProc;

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

  public AdminShoesCont() {
    System.out.println("-> AdminShoesCont created.");
  }

  private void table_paging(Model model, String word, int now_page) {
    ArrayList<ShoesVO> list = this.shoesProc.admin_list_search_paging(word, now_page, this.record_per_page);
    model.addAttribute("list", list);

    int search_count = this.shoesProc.admin_list_search_count(word);
    String paging = this.shoesProc.pagingBox(now_page, word, "/admin/shoes/admin_list_all", search_count,
        this.record_per_page, this.page_per_block);

    int no = search_count - ((now_page - 1) * this.record_per_page);

    model.addAttribute("paging", paging);
    model.addAttribute("now_page", now_page);
    model.addAttribute("word", word);
    model.addAttribute("no", no);
  }

  private void table_paging_option(Model model, int shoesno, String word, int now_page) {
    ArrayList<OptionVO> list = this.optionProc.option_paging(shoesno, word, now_page, this.record_per_page);
    model.addAttribute("list", list);

    int search_count = this.optionProc.option_search_count(shoesno);

    String paging = this.shoesProc.pagingBox(now_page, word, "/admin/shoes/admin_read/" + shoesno, search_count,
        this.record_per_page, this.page_per_block); // 2, '',

    int no = search_count - ((now_page - 1) * this.record_per_page);

    model.addAttribute("paging", paging);
    model.addAttribute("now_page", now_page);
    model.addAttribute("word", word);
    model.addAttribute("no", no);
  }

  /**
   * 등록폼 + 목록
   * 
   * @param model
   * @param shoesVO
   * @return
   */
  @GetMapping(value = "/admin_list_all")
  public String admin_list_all(HttpSession session, Model model,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    word = Tool.checkNull(word).trim();
    // shoesVO.setNamesub("-"); // 폼 초기값 설정

    table_paging(model, word, now_page);

    return "admin/shoes/admin_list_all"; // /shoes/list_search.html
  }

  /**
   * 신발 생성 폼
   */
  @GetMapping(value = "/admin_create")
  public String admin_create(HttpSession session, Model model,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesVO shoesVO = new ShoesVO();
    model.addAttribute("shoesVO", shoesVO);

    ArrayList<ShoesVO> menu = this.shoesProc.admin_list_all();
    model.addAttribute("menu", menu);

    ArrayList<Integer> list = new ArrayList<>();
    ArrayList<CategoryVO> name_list = this.categoryProc.select_name(list);
    model.addAttribute("name_list", name_list);

    table_paging(model, word, now_page);

    return "admin/shoes/admin_create";
  }

  @PostMapping("/select_subname")
  @ResponseBody
  public Map<String, Object> select_subname(@RequestBody Map<String, Object> map) {
    int categoryno = (Integer) map.get("categoryno");

    ArrayList<CategoryVO> subname_list = categoryProc.select_subname(categoryno);

    Map<String, Object> response = new HashMap<>();

    response.put("subname_list", subname_list); // 소분류 목록을 응답에 추가

    return response;
  }

  /** 카테고리 분류 추가 */
  @PostMapping(value = "/addcategory")
  @ResponseBody
  public Map<String, Object> addcategory(@RequestBody ArrayList<Integer> categorylist) {

    ArrayList<CategoryVO> name_list = this.categoryProc.select_name(categorylist);
    Map<String, Object> response = new HashMap<>();
    if (name_list.size() != 0) {

      response.put("success", true);
      response.put("name_list", name_list);
    }
    return response;
  }

  /** 신발 생성 */
  @PostMapping(value = "/admin_create")
  public Map<String, Object> create_process(HttpSession session, Model model, @RequestBody Map<String, Object> map) {

    ArrayList<Integer> categorylist = (ArrayList<Integer>) map.get("subcategorylist");
    
    MemberVO memberVO = (MemberVO)session.getAttribute("login");

    String title = (String) map.get("title");
    String brand = (String) map.get("brand");
    int price = (Integer) map.get("price");
    String contents = (String) map.get("contents");
    String visible = (String) map.get("visible");

    ShoesVO shoesVO = new ShoesVO();
    shoesVO.setMemberno(1); // 로그인으로 수정
//    shoesVO.setMemberno(memberVO.getMemberno()); // 로그인으로 수정
    shoesVO.setTitle(title);
    shoesVO.setBrand(brand);
    shoesVO.setPrice(price);
    shoesVO.setContents(contents);
    shoesVO.setVisible(visible);

    this.shoesProc.admin_create(shoesVO, categorylist);
    Map<String, Object> response = new HashMap<>();
    response.put("success", true);
    return response;
  }

  /**
   * 조회 + 목록
   * 
   * @param model
   * @param shoesno 조회할 카테고리 번호
   * @return
   */
  @GetMapping(value = "/admin_read/{shoesno}")
  public String admin_read(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesVO shoesVO = this.shoesProc.admin_read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    table_paging_option(model, shoesno, word, now_page);

    return "admin/shoes/admin_read"; //

  }

  /** 카테고리 수정 폼 */
  @GetMapping(value = "/admin_update/{shoesno}")
  public String admin_update(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesVO shoesVO = this.shoesProc.admin_read(shoesno);

    if (shoesVO.getTitle() == null) {
      model.addAttribute("code", "parent_update_fail");
      return "admin/shoes/msg";
    } else {

      model.addAttribute("shoesVO", shoesVO);
      table_paging(model, word, now_page);
      return "admin/shoes/admin_update";
    }

  }

  /** 카테고리 수정 */
  @PostMapping(value = "/admin_update")
  public String update_process(HttpSession session, Model model, @Valid ShoesVO shoesVO, BindingResult bindingResult,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    if (bindingResult.hasErrors()) {
      table_paging(model, word, now_page);
      return "admin/shoes/admin_update";
    }

    int cnt = this.shoesProc.admin_update(shoesVO);
    if (cnt == 1) {
      return "redirect:/admin/shoes/admin_read/" + shoesVO.getShoesno() + "?word=" + Tool.encode(word) + "&now_page="
          + now_page;
    } else {
      model.addAttribute("code", "update_fail");
      return "admin/shoes/msg";
    }
  }

  /** 카테고리 삭제 폼 */
  @GetMapping(value = "/admin_delete/{shoesno}")
  public String admin_delete(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    int parent_count = this.shoesProc.parent_count(shoesno);
    if (parent_count == 0) {
      ShoesVO shoesVO = this.shoesProc.admin_read(shoesno);
      model.addAttribute("shoesVO", shoesVO);

      table_paging(model, word, now_page);

      return "admin/shoes/admin_delete";
    } else {
      model.addAttribute("code", "parent_delete_fail");
      return "admin/shoes/msg";
    }

  }

  /** 카테고리 삭제 */
  @PostMapping(value = "/admin_delete")
  public String admiin_delete_process(HttpSession session, Model model, @RequestParam("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    int cnt = this.shoesProc.admin_delete(shoesno);

    if (cnt == 1) {
      return "redirect:/admin/shoes/admin_list_all?now_page=1";
    } else {
      model.addAttribute("code", "delete_fail");
      return "admin/shoes/msg";
    }
  }

  @GetMapping(value = "/option_create/{shoesno}")
  public String option_create(HttpSession session, Model model, @PathVariable("shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ShoesVO shoesVO = this.shoesProc.admin_read(shoesno);
    model.addAttribute("shoesVO", shoesVO);

    ArrayList<OptionVO> optionList = this.optionProc.option_list(now_page);
    model.addAttribute("optionList", optionList);

    OptionVO optionVO = new OptionVO();
    optionVO.setShoesno(shoesno);

    model.addAttribute("optionVO", optionVO);

    table_paging(model, word, now_page);

    return "admin/shoes/option_create";
  }

  /** 옵션 생성 */
  @PostMapping(value = "/option_create")
  public String option_create_process(HttpSession session, Model model, @Valid OptionVO optionVO,
      BindingResult bindingResult, @RequestParam(name = "shoesno") Integer shoesno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    if (bindingResult.hasErrors()) {
      return "admin/shoes/option_create";
    }

    int cnt = this.optionProc.option_create(optionVO);
    if (cnt == 1) {
      return "redirect:/admin/shoes/admin_read/" + shoesno;
    } else {
      model.addAttribute("code", "create_fail");
      return "admin/shoes/msg";
    }

  }

  /** 옵션 수정 */
  @PostMapping(value = "/option_update")
  @ResponseBody
  public Map<String, Object> option_update(HttpSession session, Model model, @RequestBody Map<String, Object> map) {

    int optionno = (Integer) map.get("optionno");
    int amount = (Integer) map.get("amount");

    Map<String, Object> response = new HashMap<>();
    int cnt = this.optionProc.option_update(amount, optionno);
    if (cnt == 1) {
      response.put("success", true);
    }

    return response;
  }

  /** 옵션 삭제 */
  @PostMapping(value = "/option_delete")
  @ResponseBody
  public Map<String, Object> option_delete(HttpSession session, Model model, @RequestBody Map<String, Object> map) {

    int optionno = (Integer) map.get("optionno");

    Map<String, Object> response = new HashMap<>();
    int cnt = this.optionProc.option_delete(optionno);
    if (cnt == 1) {
      response.put("success", true);
    }

    return response;
  }
}
