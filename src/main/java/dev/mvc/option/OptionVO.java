package dev.mvc.option;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OptionVO {
  
  

//  
//  /** 신발명 */
//  private String title;
//  
//  /** 신발 브랜드명 */
//  private String brand;
//  
//  
//  /** 신발 평점*/
//  private Double rating;
//  
//  
//  /** 신발 가격*/
//  private Double price;
//  
//  
//  /** 신발 할인율 */
//  private Double discount;
//  
//  
//  /** 신발 설명 */
//  private String contents;
//  
//  
//  /** 신발 판매 여부 */
//  private char visible = 'Y';
//  
//  
//  /** 유저 번호 */
//  private Integer memberno;
//  
//  
//  /** 카테고리 번호 */
//  private Integer categoryno;
  
  //---------------------------------------------
  
  
  /** 옵션 번호 */
  private Integer optionno;
  
  
  /** 신발 사이즈 */
  private Integer sizes;
  
  
  /** 신발 재고 */
  private Integer amount;
  
  
  /** 신발 색상 */
  private String color;
  
  /** 신발 번호 */
  private Integer shoesno;
  
  

}
