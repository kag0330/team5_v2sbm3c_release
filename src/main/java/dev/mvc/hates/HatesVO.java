package dev.mvc.hates;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//CREATE TABLE HATES(
//    HATESNO                           NUMBER(9)    NULL      PRIMARY KEY,
//    REVIEWNO                      NUMBER(9) NOT NULL,
//    MEMBERNO                      NUMBER(9) NOT NULL,
//  FOREIGN KEY (REVIEWNO) REFERENCES REVIEW (REVIEWNO),
//FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
//);
public class HatesVO {
  /** 좋아요 번호 */
  private Integer hatesno;
  
  /** 후기 번호 */
  private Integer reviewno;
  
  /** 멤버 번호 */
  private Integer memberno;
}
