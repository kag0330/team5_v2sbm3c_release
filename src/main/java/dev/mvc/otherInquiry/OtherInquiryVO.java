package dev.mvc.otherInquiry;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/*CREATE TABLE OTHER_INQUIRY(
    OTHER_INQUIRY_NO        NUMBER(9)        NOT NULL PRIMARY KEY,
    TITLE                   VARCHAR2(100)    NOT NULL,
    CONTENTS                VARCHAR2(1000)   NOT NULL,
    RDATE                   DATE             NOT NULL,
    ANSWER_VISIBLE          CHAR(1)          DEFAULT 'N'   NOT NULL,
    ANSWER_CONTENTS          VARCHAR2(1000)   DEFAULT '',
    MEMBERNO                NUMBER(9),   
    FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);
*/
@Setter @Getter
public class OtherInquiryVO {
  /** 기타 문의 번호 */
  private Integer other_inquiry_no;
  
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
  
  /** 멤버 번호 */
  private Integer memberno;
}
