package dev.mvc.shoesInquiry;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/*CREATE TABLE SHOES_INQUIRY(
    SHOES_INQUIRY_NO        NUMBER(9)        NOT NULL PRIMARY KEY,
    TITLE                   VARCHAR2(100)    NOT NULL,
    CONTENTS                VARCHAR2(1000)   NOT NULL,
    RDATE                   DATE             NOT NULL,
    ANSWER_VISIBLE          CHAR(1)          DEFAULT 'N'   NOT NULL,
    ANSWER_COMMENT          VARCHAR2(1000)   DEFAULT '',
    OPTIONNO                NUMBER(9)        NULL,
    MEMBERNO                NUMBER(9)        NULL,
    FOREIGN KEY (OPTIONNO) REFERENCES OPTIONS (OPTIONNO),
    FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);
*/
@Setter @Getter
public class ShoesInquiryVO {
  /** 신발 문의 번호 */
  private Integer shoes_inquiry_no;
  
  /** 문의 제목 */
  private String title;
  
  /** 문의 내용 */
  private String contents;
  
  /** 문의 작성일 */
  private Date rdate;
  
  /** 답변 여부 */
  private char answer_visible;
  
  /** 답변 내용 */
  private String answer_contents;
  
  /** 신발 번호 */
  private Integer shoesno;
  
  /** 멤버 번호 */
  private Integer memberno;
  
}
