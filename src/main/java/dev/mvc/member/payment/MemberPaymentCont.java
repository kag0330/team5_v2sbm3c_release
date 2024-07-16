   package dev.mvc.member.payment;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.member.MemberVO;
import dev.mvc.payment.PaymentProcInter;
import dev.mvc.payment.PaymentVO;
import dev.mvc.paymentDetails.PaymentDetailsProcInter;
import dev.mvc.paymentTotal.PaymentTotalProcInter;
import dev.mvc.paymentTotal.PaymentTotalVO;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("member/payment")
public class MemberPaymentCont {
  @Autowired
  @Qualifier("dev.mvc.payment.PaymentProc")
  private PaymentProcInter paymentProc;
  
  @Autowired
  @Qualifier("dev.mvc.paymentDetails.PaymentDetailsProc")
  private PaymentDetailsProcInter paymentDetailsProc;
  
  @Autowired
  @Qualifier("dev.mvc.paymentTotal.PaymentTotalProc")
  private PaymentTotalProcInter paymentTotalProc;
  
  @GetMapping("order")
  public String order(HttpSession session, Model model, 
                      @RequestParam(required = false, defaultValue = "7", name = "dates")Integer dates,
                      @RequestParam(required = false, name="search")String search ) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    ArrayList<PaymentTotalVO> list = this.paymentTotalProc.list(memberVO.getMemberno(), dates, search);
    System.out.println(list.toString());
    model.addAttribute("cnt", list.size());
    model.addAttribute("startDates",Tool.addDays(new Date(),-(dates)));
    model.addAttribute("paymentsList", list);
    
    return "member/payment/order";
  }
  
  @GetMapping("csorder")
  public String csorder(HttpSession session, Model model, 
                      @RequestParam(required = false, defaultValue = "7", name = "dates")Integer dates,
                      @RequestParam(required = false, name="search")String search ) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    ArrayList<PaymentTotalVO> list = this.paymentTotalProc.cslist(memberVO.getMemberno(), dates, search);
    System.out.println(list.toString());
    model.addAttribute("cnt", list.size());
    model.addAttribute("startDates",Tool.addDays(new Date(),-(dates)));
    model.addAttribute("paymentsList", list);
    
    return "member/payment/csorder";
  }
  
  
  @ResponseBody
  @PostMapping("delete")
  public ResponseEntity<String> delete(@RequestParam("no")int paymentno){
    if(this.paymentDetailsProc.delete(paymentno) == 0 && this.paymentProc.delete(paymentno) == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문내역을 찾을 수 없음");
    }
    
    return ResponseEntity.ok("삭제 성공");
  }
  
  @ResponseBody
  @PostMapping("cancel")
  public ResponseEntity<String> cancel(@RequestParam("no")int paymentno){
    if(this.paymentProc.cancel(paymentno) == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문내역을 찾을 수 없음");
    }
    
    return ResponseEntity.ok("취소 성공");
  }
}
