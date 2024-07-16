package dev.mvc.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.member.MemberVO;
import dev.mvc.team5.DefaultCont;
import dev.mvc.tool.Alert;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/report")
@Controller
public class ReportCont {
  @Autowired
  @Qualifier("dev.mvc.report.ReportProc")
  private ReportProcInter reportProc;

  public int record_per_page = 5;
  public int page_per_block = 5;

  @PostMapping(value = "/report_count")
  @ResponseBody
  public int report_count(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    // session의 memberno 값 받아오기
    // session.getAttribute("memberno");
    int memberno = 1;
    int reviewno = Integer.parseInt(map.get("reviewno"));

    int cnt = this.reportProc.report_count(memberno, reviewno);
    return cnt;
  }

  @PostMapping(value = "/create")
  @ResponseBody
  public Map<String, Object> create(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    Map<String, Object> response = new HashMap<>();

    // 신고 생성
    ReportVO reportVO = new ReportVO();
    reportVO.setTitle(map.get("title"));
    reportVO.setContents(map.get("contents"));
    reportVO.setTypeno(Integer.parseInt(map.get("typeno")));
    reportVO.setMemberno(1);
    reportVO.setReviewno(Integer.parseInt(map.get("reviewno")));
    int cnt = this.reportProc.create(reportVO);

    if (cnt == 1) {
      response.put("success", true);
    }

    return response;
  }

  @GetMapping("/myReport")
  public String myReport(HttpSession session, Model model,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    if (memberVO != null) {
      int memberno = memberVO.getMemberno();
      ArrayList<ReportInfoVO> list = this.reportProc.myReport(memberno);
      model.addAttribute("list", list);
      int search_count = this.reportProc.myReportCount(memberno);
      String paging = this.reportProc.pagingBox(now_page, "", "/report/myReport", search_count, this.record_per_page,
          this.page_per_block);

      int no = search_count - ((now_page - 1) * this.record_per_page);

      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);
      model.addAttribute("no", no);

      return "report/myReport";
    } else {
      Alert message = new Alert("로그인 후 이용해주세요.", "/login/signin", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }

  }
}
