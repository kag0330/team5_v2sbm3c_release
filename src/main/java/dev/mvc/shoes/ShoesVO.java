package dev.mvc.shoes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ShoesVO {
  /** 신발 번호 */
  private Integer shoesno = 0;
  
  /** 신발명 */
  private String title = "";
  
  /** 신발 브랜드명 */
  private String brand = "";
  
  /** 신발 평점*/
  private Double rating = (double) 0;
  
  /** 신발 가격*/
  private Integer price = 0;
  
  /** 신발 할인율 */
  private Double discount = (double) 0;
  
  /** 신발 설명 */
  private String contents = "";
  
  /** 신발 판매 여부 */
  private String visible = "Y";
  
  /** 유저 번호 */
  private Integer memberno = 0;
  
  /** 카테고리 번호 */
  private Integer categoryno = 0;
  
  /** 색상 */
  private String color;

  /** sizes */
  private String sizes;

  /** 신발 판매량 */
  private int saleCount;
  
  
}
