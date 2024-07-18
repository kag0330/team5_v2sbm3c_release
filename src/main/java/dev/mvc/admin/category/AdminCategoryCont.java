package dev.mvc.admin.category;

import java.util.ArrayList;
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
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/admin/category")
@Controller
public class AdminCategoryCont {

  @Autowired
  @Qualifier("dev.mvc.category.CategoryProc")
  private CategoryProcInter categoryProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 10;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 5;

  public AdminCategoryCont() {
    System.out.println("-> Category created.");
  }

  private void table_paging(Model model, String word, int now_page) {
    ArrayList<CategoryVO> list = this.categoryProc.list_search_paging(word, now_page, this.record_per_page);
    if (list.isEmpty()) {
    } else {
      System.out.println("list" + list);
      model.addAttribute("list", list);

      int search_count = this.categoryProc.list_search_count(word);
      String paging = this.categoryProc.pagingBox(now_page, word, "/admin/category/list", search_count,
          this.record_per_page, this.page_per_block);

      int no = search_count - ((now_page - 1) * this.record_per_page);

      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      model.addAttribute("word", word);
      model.addAttribute("no", no);

    }
  }

  /** 카테고리 목록 */
  @GetMapping(value = "/list")
  public String list(HttpSession session, Model model, CategoryVO categoryVO,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    word = Tool.checkNull(word).trim();

    ArrayList<CategoryVO> menu = this.categoryProc.list_all();
    model.addAttribute("menu", menu);

    table_paging(model, word, now_page);

    return "admin/category/list";
  }

  /** 카테고리 생성폼 */
  @GetMapping(value = "/create")
  public String create(HttpSession session, Model model) {

    CategoryVO categoryVO = new CategoryVO();
    model.addAttribute("categoryVO", categoryVO);
    return "admin/category/create";
  }

  /** 카테고리명 중복 체크 */
  @PostMapping(value = "/checkName")
  @ResponseBody
  public String checkName(@RequestBody Map<String, String> request) {
    String name = request.get("name");
    ArrayList<String> namelist = this.categoryProc.name_list();

    for (int i = 0; i < namelist.size(); i++) {
      if (name.equals(namelist.get(i))) {
        return "old";
      }
    }
    return "new";
  }

  /** 서브 카테고리명 중복 체크 */
  @PostMapping(value = "/checkSubName")
  @ResponseBody
  public String checkSubName(@RequestBody Map<String, String> request) {
    String name = request.get("name");
    String subname = request.get("subname");

    int categoryno = this.categoryProc.parent_categoryno(name);
    ArrayList<String> subnamelist = this.categoryProc.subname_list(categoryno);
    if (subnamelist.isEmpty()) {
      return "new";
    } else {
      for (int i = 0; i < subnamelist.size(); i++) {
        System.out.println(subnamelist.get(i));
        if (subname.equals(subnamelist.get(i))) {
          return "old";
        }
      }
      return "new";
    }

  }

  /** 카테고리 생성 */
  @PostMapping(value = "/create")
  public String create_process(HttpSession session, Model model, @Valid CategoryVO categoryVO,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "admin/category/create";
    }

    int cnt = this.categoryProc.create(categoryVO);
    if (cnt == 1) {
      return "redirect:/admin/category/list?now_page=1";
    } else {
      model.addAttribute("code", "create_fail");
      return "admin/category/msg";
    }
  }

  /** 카테고리 읽기 */
  @GetMapping(value = "/read/{categoryno}")
  public String read(HttpSession session, Model model, @PathVariable("categoryno") Integer categoryno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    ArrayList<CategoryVO> menu = this.categoryProc.list_all();
    model.addAttribute("menu", menu);

    CategoryVO categoryVO = this.categoryProc.read(categoryno);
    model.addAttribute("categoryVO", categoryVO);

    table_paging(model, word, now_page);

    return "admin/category/read";
  }

  /** 카테고리 수정 폼 */
  @GetMapping(value = "/update/{categoryno}")
  public String update(HttpSession session, Model model, @PathVariable("categoryno") Integer categoryno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    CategoryVO categoryVO = this.categoryProc.read(categoryno);

    if (categoryVO.getParentno() == null) {
      model.addAttribute("code", "parent_update_fail");
      return "admin/category/msg";
    } else {

      model.addAttribute("categoryVO", categoryVO);
      table_paging(model, word, now_page);
      return "admin/category/update";
    }

  }

  /** 카테고리 수정 */
  @PostMapping(value = "/update")
  public String update_process(HttpSession session, Model model, @Valid CategoryVO categoryVO,
      BindingResult bindingResult, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    if (bindingResult.hasErrors()) {
      table_paging(model, word, now_page);
      return "admin/category/update";
    }

    int cnt = this.categoryProc.update(categoryVO);
    if (cnt == 1) {
      return "redirect:/admin/category/read/" + categoryVO.getCategoryno() + "?word=" + Tool.encode(word) + "&now_page="
          + now_page;
    } else {
      model.addAttribute("code", "update_fail");
      return "admin/category/msg";
    }
  }

  /** 카테고리 삭제 폼 */
  @GetMapping(value = "/delete/{categoryno}")
  public String delete(HttpSession session, Model model, @PathVariable("categoryno") Integer categoryno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    int parent_count = this.categoryProc.parent_count(categoryno);
    if (parent_count == 0) {
      CategoryVO categoryVO = this.categoryProc.read(categoryno);
      model.addAttribute("categoryVO", categoryVO);

      table_paging(model, word, now_page);

      return "admin/category/delete";
    } else {
      model.addAttribute("code", "parent_delete_fail");
      return "admin/category/msg";
    }

  }

  /** 카테고리 삭제 */
  @PostMapping(value = "/delete")
  public String delete_process(HttpSession session, Model model, @RequestParam("categoryno") Integer categoryno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    int cnt = this.categoryProc.delete(categoryno);

    if (cnt == 1) {
      return "redirect:/admin/category/list?now_page=1";
    } else {
      model.addAttribute("code", "delete_fail");
      return "admin/category/msg";
    }
  }

}
