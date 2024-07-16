package dev.mvc.paymentTotal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import dev.mvc.member.MemberVO;
import dev.mvc.payment.PaymentVO;
import dev.mvc.paymentDetails.PaymentDetailsVO;
import dev.mvc.shoes.ShoesVO;
import dev.mvc.shoesFile.ShoesFileVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import dev.mvc.option.OptionVO;

@Getter @Setter @ToString
public class PaymentTotalVO {
  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public static int RECORD_PER_PAGE = 10;
  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public static int PAGE_PER_BLOCK = 10;
  
  private Integer memberno;
  private String memberid;
  private String nickname;
  
  private Integer paymentno;
  private Date rdate;
  private String status;
  private String payment_status;
  private String cs_status;
  private Double total_price;
  private Double delivery;
  private Double total_payment;
  
  private ArrayList<PaymentDetailsOptionVO> payment_details_option; 
}
