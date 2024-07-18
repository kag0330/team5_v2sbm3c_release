package dev.mvc.admin.notice;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.notice.NoticeMemberFileVO;
import dev.mvc.notice.NoticeMemberVO;
import dev.mvc.notice.NoticeProcInter;
import dev.mvc.noticeFile.NoticeFileVO;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/admin/notice")
@Controller
public class AdminNoticeCont {

  @Autowired
  @Qualifier("dev.mvc.notice.NoticeProc")
  private NoticeProcInter noticeProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 10;

  /** 블럭당 페이지 수, 하나의 블럭은 5개의 페이지로 구성됨 */
  public int page_per_block = 5;

  public AdminNoticeCont() {
    System.out.println("-> Notice created.");
  }

  private void table_paging(Model model, String word, int now_page) {
    ArrayList<NoticeMemberFileVO> list = this.noticeProc.list_search_paging(word, now_page, this.record_per_page);
    model.addAttribute("list", list);
    if (list.isEmpty()) {
    } else {
      model.addAttribute("list", list);

      int search_count = this.noticeProc.list_search_count(word);
      String paging = this.noticeProc.pagingBox(now_page, word, "/admin/notice/list", search_count, this.record_per_page, this.page_per_block);

      int no = search_count - ((now_page - 1) * this.record_per_page);

      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      model.addAttribute("word", word);
      model.addAttribute("no", no);

    }

  }

  @GetMapping(value = "list")
  public String list(HttpSession session, Model model, NoticeMemberVO noticememberVO, @RequestParam(name = "word", defaultValue = "") String word, @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    word = Tool.checkNull(word).trim();

    ArrayList<NoticeMemberVO> menu = this.noticeProc.list_all();
    model.addAttribute("menu", menu);

    table_paging(model, word, now_page);

    return "admin/notice/list";
  }

  /** 공지사항 생성폼 */
  @GetMapping(value = "/create")
  public String create(HttpSession session, Model model) {
    return "admin/notice/create";
  }

  /** 공지사항 생성 */
  @ResponseBody
  @PostMapping(value = "/create", consumes = "multipart/form-data")
  public boolean create_process(@RequestParam("title") String title, @RequestParam("contents") String contents, @RequestParam(name = "files", required = false) MultipartFile[] files) {

    Map<String, Object> map = new HashMap<>();
    map.put("title", title);
    map.put("contents", contents);
    if (files != null && files.length > 0) {
      map.put("files", files);
    }

    System.out.println(map.toString());

    if (this.noticeProc.create(map) == 0)
      return false;

    return true;
  }

  /** 카테고리 읽기 */
  @GetMapping(value = "/read/{noticeno}")
  public String read(HttpSession session, Model model, @PathVariable("noticeno") Integer noticeno, @RequestParam(name = "word", defaultValue = "") String word, @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    /*
     * ArrayList<CategoryVO> menu = this.noticeP.list_all();
     * model.addAttribute("menu", menu);
     */

    int count = this.noticeProc.file_count(noticeno);
    NoticeMemberFileVO noticememberfileVO = this.noticeProc.read(noticeno);
    model.addAttribute("noticememberfileVO", noticememberfileVO);

    table_paging(model, word, now_page);

    return "admin/notice/read";
  }

  @GetMapping(value = "/update/{noticeno}")
  public String update_form(HttpSession session, Model model, @PathVariable("noticeno") Integer noticeno, @RequestParam(name = "word", defaultValue = "") String word, @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    /*
     * ArrayList<CategoryVO> menu = this.noticeP.list_all();
     * model.addAttribute("menu", menu);
     */

    int count = this.noticeProc.file_count(noticeno);
    NoticeMemberFileVO noticememberfileVO = this.noticeProc.read(noticeno);
    model.addAttribute("noticememberfileVO", noticememberfileVO);
    table_paging(model, word, now_page);

    return "admin/notice/update";
  }

  @ResponseBody
  @PostMapping(value = "/update")
  public Boolean update(@RequestParam("noticeno") Integer noticeno,
                        @RequestParam("title") String title, 
                        @RequestParam("contents") String contents, 
                        @RequestParam(name = "files", required = false) MultipartFile[] files, 
                        @RequestParam(name = "deletefiles", required = false) Integer[] deletefiles) {
    Map<String, Object> map = new HashMap<>();

    map.put("noticeno", noticeno);
    map.put("title", title);
    map.put("contents", contents);
    map.put("files", files);
    map.put("deletefiles", deletefiles);

    System.out.println(map.toString());
    return this.noticeProc.update(map);

//    return true;
  }

  @GetMapping(value = "/delete/{noticeno}")
  public String delete_form(HttpSession session, Model model, @PathVariable("noticeno") Integer noticeno, @RequestParam(name = "word", defaultValue = "") String word, @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    /*
     * ArrayList<CategoryVO> menu = this.noticeP.list_all();
     * model.addAttribute("menu", menu);
     */

    NoticeMemberFileVO noticememberfileVO = this.noticeProc.read(noticeno);
    model.addAttribute("noticememberfileVO", noticememberfileVO);
    table_paging(model, word, now_page);

    return "admin/notice/delete";
  }

  /** 카테고리 삭제 */
  @PostMapping(value = "/delete")
  public String delete_process(HttpSession session, Model model, @RequestParam("noticeno") Integer noticeno, @RequestParam(name = "word", defaultValue = "") String word, @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    this.noticeProc.delete(noticeno);
    return "redirect:/admin/notice/list?now_page=1";
  }
}
