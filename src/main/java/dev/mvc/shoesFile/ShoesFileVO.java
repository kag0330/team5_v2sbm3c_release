package dev.mvc.shoesFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


//CREATE TABLE SHOES_FILE(
//    SHOES_FILE_NO                        NUMBER(9)    NOT NULL    PRIMARY KEY,
//    NAME                                 VARCHAR2(100)    NOT NULL,
//    SIZE                                 NUMBER     NOT NULL,
//    EX                                   VARCHAR2(100)    NOT NULL,
//    SRC                                  VARCHAR2(100)    NOT NULL,
//    SHOESNO                              NUMBER(9)    NULL ,
//  FOREIGN KEY (S_NO) REFERENCES SHOSE (S_NO)
//);
@Getter @Setter @ToString
public class ShoesFileVO {
  /** 신발 파일 번호 */
  private Integer shoes_file_no;
  
  
  /** 파일명 */
  private String name;
  
  
  /** 파일크기 */
  private Long sizes;
  
  /** 확장자명 */
  private String ex;
  
  
  /** 파일주소 */
  private String src;
  
  
  /** 신발 번호 */
  private Integer shoesno;
}
