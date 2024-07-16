package dev.mvc.admin.report;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.mvc.report.ReportInfoVO;
import dev.mvc.report.ReportProcInter;
import dev.mvc.report.ReportVO;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RequestMapping("/admin/report")
@Controller
public class AdminReportCont {

  @Autowired
  @Qualifier("dev.mvc.report.ReportProc")
  private ReportProcInter reportProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 5;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 5;

  public AdminReportCont() {
    System.out.println("-> AdminReport created.");
  }

  /** 신고 페이징 */
  private void report_table_paging(Model model, String word, int now_page) {
    ArrayList<ReportInfoVO> list = this.reportProc.list_search_paging(word, now_page, this.record_per_page);

    if (list.isEmpty()) {
    } else {
      model.addAttribute("list", list);

      int search_count = this.reportProc.list_search_count(word);
      String paging = this.reportProc.pagingBox(now_page, word, "/admin/report/list", search_count,
          this.record_per_page, this.page_per_block);

      int no = search_count - ((now_page - 1) * this.record_per_page);

      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      model.addAttribute("word", word);
      model.addAttribute("no", no);
    }

  }

  /** 신고 목록 */
  @GetMapping(value = "/list")
  public String report_list(HttpSession session, Model model,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    word = Tool.checkNull(word).trim();
    
    report_table_paging(model, word, now_page);

    return "admin/report/list";
  }
  
  @GetMapping(value = "/{reportno}")
  public String read(HttpSession session, Model model, @PathVariable("reportno") Integer reportno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    System.out.println("reportno" + reportno);
    ReportInfoVO reportInfoVO = this.reportProc.read(reportno);
    model.addAttribute("reportInfoVO", reportInfoVO);
    
    report_table_paging(model, word, now_page);
    return "admin/report/read";
  }

  /** 신발 문의 답변 */
  @PostMapping(value = "/answer")
  public String report_answer(HttpSession session, Model model, @Valid ReportInfoVO reportInfoVO,
      BindingResult bindingResult, @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    ReportVO reportVO = reportInfoVO.getReportVO();
    
    this.reportProc.answer(reportVO.getReportno(), 'Y', reportVO.getReport_contents());
    return "redirect:/admin/report/" + reportVO.getReportno() + "?word=" + word + "&now_page="
        + now_page;
  }
  
}
