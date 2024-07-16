package dev.mvc.basket;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE BASKET(
//    B_NO                              NUMBER(9)    NOT NULL    PRIMARY KEY,
//    B_AMOUNT                          NUMBER(9)    NOT NULL,
//    M_NO                              NUMBER(9)    NOT NULL,
//    O_NO                              NUMBER(9)    NOT NULL,
//  FOREIGN KEY (M_NO) REFERENCES MEMBER (M_NO),
//  FOREIGN KEY (O_NO) REFERENCES OPTION (O_NO)
//);
public class BasketVO {
  /** 장바구니 번호 */
  private Integer basketno;
  
  /** 신발 수량 */
  private Integer amount;
  
  /** 멤버 번호 */
  private Integer memberno;
  
  /** 옵션 번호 */
  private Integer optionno;
  
  private Integer shoesno;
  
  private Integer sizes;
  
  private String color;
  
  
}
