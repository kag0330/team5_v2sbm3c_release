package dev.mvc.admin.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.member.MemberVO;
import dev.mvc.payment.PaymentProcInter;
import dev.mvc.paymentTotal.PaymentTotalProc;
import dev.mvc.paymentTotal.PaymentTotalProcInter;
import dev.mvc.paymentTotal.PaymentTotalVO;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin/payment")
public class AdminPaymentCont {
  @Autowired
  @Qualifier("dev.mvc.payment.PaymentProc")
  private PaymentProcInter paymentProc;
  
  @Autowired
  @Qualifier("dev.mvc.paymentTotal.PaymentTotalProc")
  private PaymentTotalProcInter paymentTotalProc;
  
  /* =========================================== ModelAttribute START =========================================== */
  /**
   * 목록(List) 출력 <br>
   * http://localhost:9093/admin/payment/** <br>
   * http://54.180.175.50:9093/admin/payment/**
   * 
   * @param now_page 현재 페이지
   * @param word     검색어
   * @param key      키워드 선택
   */
//  @ModelAttribute("list")
//  public ArrayList<PaymentTotalVO> paymentlist(@RequestParam(name = "now_page", required = false, defaultValue = "1") Integer now_page,
//                                               @RequestParam(name = "word", required = false, defaultValue = "") String word) {
//    return this.paymentTotalProc.listAdminPaging(word, now_page, PaymentTotalVO.RECORD_PER_PAGE, new HashMap<String,Object>());
//  }
//  
//  
//  
//  @ModelAttribute("count")
//  public Integer count(@RequestParam(name = "now_page", required = false, defaultValue = "1") Integer now_page,
//                       @RequestParam(name = "word", required = false, defaultValue = "") String word) {
//    
//    return this.paymentTotalProc.count(word);
//  }
//  
//  
//  @ModelAttribute("paging")
//  public String paging(HttpServletRequest request, 
//                       @RequestParam(name = "now_page", required = false, defaultValue = "1") Integer now_page,
//                       @RequestParam(name = "word",     required = false, defaultValue = "")  String word) {
//    int count = this.paymentTotalProc.count(word);
//    String path = request.getServletPath();
//    
//    return this.paymentProc.pagingBox(now_page, word, path, count);
//  }



  /* =========================================== ModelAttribute END =========================================== */

  @GetMapping("list")
  public String list(@RequestParam(name = "now_page", required = false, defaultValue = "1") Integer now_page,
                     @RequestParam(name = "word",     required = false, defaultValue = "")  String word) {
    return "admin/payment/list";
  }
  
  @PostMapping("list")
  public String listProc(@RequestParam(name = "now_page", required = false, defaultValue = "1") Integer now_page,
                         @RequestParam(name = "word",     required = false, defaultValue = "")  String word) {
    return "redirect:/admin/payment/list?now_page=" + now_page + "&word=" + word;  
  }
  
  @ResponseBody
  @PostMapping("payment")
  public String paymentlist(@RequestBody Map<String,Object> map) {
    return this.paymentTotalProc.paymentAjax(map);
  }
  
  @ResponseBody
  @PostMapping("{memberno}/paymentDetails")
  public String paymentDetailslist(@PathVariable("memberno")int memberno, @RequestBody Map<String, Object> map) {
    return this.paymentTotalProc.paymentDetailsAjax(memberno, map);
  }
  
  @ResponseBody
  @PostMapping("update")
  public String updateProc(@RequestBody Map<String, Object> map){
    if(this.paymentProc.update(map) > 0) {
      return "OK";
    }
    return "FAIL";
  }
  
  @ResponseBody
  @PostMapping("count")
  public String count(@RequestBody Map<String,Object> map) {
    int membercnt = this.paymentTotalProc.member_cnt(map);
    int paymentcnt = this.paymentTotalProc.payment_cnt(map);
    return membercnt + " 명 (" + paymentcnt + ")";
  }
}
