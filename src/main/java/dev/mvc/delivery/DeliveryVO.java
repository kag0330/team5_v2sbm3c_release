package dev.mvc.delivery;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE DELIVERY(
//    DELIVERYNO                        NUMBER(9)    NOT NULL    PRIMARY KEY,
//    TITLE                             VARCHAR2(100)    NOT NULL,
//    RECIPIENT                         VARCHAR2(100)    NOT NULL,
//    PHONE                             VARCHAR2(13)     NOT NULL,
//    ZIPCODE                           NUMBER(5)    NOT NULL,
//    ADDR1                             VARCHAR2(1000)     NOT NULL,
//    ADDR2                             VARCHAR2(1000)     NOT NULL,
//    REQUESTS                          VARCHAR2(1000)     NULL ,
//    MEMBERNO                          NUMBER(9)    NOT NULL,
//  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
//);
@Getter @Setter @ToString
public class DeliveryVO {
  /** 배송지주소번호*/
  private Integer deliveryno;
  
  /** 배송지이름*/
  private String title;
  
  /** 받는사람 */
  private String recipient;
  
  /** 받는사람 폰*/
  private String phone;
  
  /** 우편번호 */
  private Integer zipcode;
  
  /** 주소1 */
  private String addr1;
  
  /** 주소2 */
  private String addr2;
  
  /** 요청 사항 */
  private String requests;
  
  /** 멤버번호 */
  private Integer memberno;
}
