package dev.mvc.paymentInquiry;

import dev.mvc.member.MemberVO;
import dev.mvc.option.OptionVO;
import dev.mvc.payment.PaymentVO;
import dev.mvc.paymentDetails.PaymentDetailsVO;
import dev.mvc.shoes.ShoesVO;
import dev.mvc.shoesFile.ShoesFileVO;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class PaymentInquiryInfoVO {
  private PaymentInquiryVO paymentInquiryVO;
  private PaymentVO paymentVO;
  private PaymentDetailsVO paymentDetailsVO;
  private ShoesVO shoesVO;
  private OptionVO optionVO;
  private MemberVO memberVO;
  private ShoesFileVO shoesFileVO;
}
