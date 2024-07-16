package dev.mvc.member.inquiry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member/inquiry")
public class MemberInquiryCont {
  @GetMapping({"","/"})
  public String inquiry() {
    return "member/inquiry/inquiry";
  }
  
  @GetMapping("paymentinquiry")
  public String paymentInquiry() {
    return "member/inquiry/paymentinquiry";
  }
}
