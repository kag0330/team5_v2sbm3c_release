package dev.mvc.payment;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE PAYMENT(
//    PAYMENTNO                         NUMBER(9)        NOT NULL    PRIMARY KEY,
//    RDATE                             DATE             NOT NULL,
//    STATUS                            VARCHAR2(50)     NOT NULL,
//    PAYMENT_STATUS                    VARCHAR2(50)     NOT NULL,
//    CS_STATUS                         VARCHAR2(50)     NULL ,
//    TOTAL_PRICE                         NUMBER             DEFAULT 0       NULL  , 
//    DELIVERY                          NUMBER             DEFAULT 2500    NULL,
//    TOTAL_PAYMENT                     NUMBER             DEFAULT 0       NOT NULL,
//    MEMBERNO                          NUMBER(9)        NOT NULL,
//  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
//);
public class PaymentVO {
  /** 주문번호 */
  private Integer paymentno;
  
  
  /** 주문일자 */
  private Date rdate;
  
  
  /** 
   * 주문상태 <br>
   * 상품준비중 / 배송준비중 / 배송보류 / 배송대기 / 배송중 / 배송완료
   */
  private String status;
  
  
  /** 결제상태 <br>
   * 입금전 / 추가입금대기 / 입금완료(수동) / 입금완료(자동) / 결제완료
   */
  private String payment_status;
  
  
  /** CS상태 <br>
   * 취소 / 교환 / 반품 / 환불 
   */
  private String cs_status;
  
  
  /** 총 상품금액 <br>
   *  상품금액들의 합
   */
  private Double total_price;
  
  
  /** 배송비 */
  private Double delivery;
  
  
  /** 총 주문금액 <br>
   *  총상품금액 + 배송비
   */
  private Double total_payment;
  
  
  /** 멤버번호 */
  private Integer memberno;
}
