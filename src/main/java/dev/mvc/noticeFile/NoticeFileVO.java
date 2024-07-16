package dev.mvc.noticeFile;

import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import dev.mvc.notice.NoticeVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*CREATE TABLE NOTICE_FILE(
    NOTICE_FILE_NO                    NUMBER(9)    NOT NULL    PRIMARY KEY,
    NAME                              VARCHAR2(100)    NOT NULL,
    SIZES                             NUMBER     NOT NULL,
    EX                                VARCHAR2(100)    NOT NULL,
    SRC                               VARCHAR2(100)    NOT NULL,
    NOTICENO                          NUMBER(9)    NULL ,
  FOREIGN KEY (NOTICENO) REFERENCES NOTICE (NOTICENO)
);*/
@Getter @Setter @ToString
public class NoticeFileVO {
  /** 파일번호 */
  private Integer notice_file_no;
  
  /** 파일명 */
  private String name;
  
  /** 파일크기 */
  private Long sizes;
  
  /** 확장자명 */
  private String ex;
  
  /** 파일 주소 */
  private String src;
  
  /** 후기 번호 */
  private Integer noticeno;
  
  private MultipartFile fileSelect;
  
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NoticeFileVO that = (NoticeFileVO) o;
    return notice_file_no == that.notice_file_no &&
           sizes == that.sizes &&
           Objects.equals(name, that.name) &&
           Objects.equals(ex, that.ex) &&
           Objects.equals(src, that.src);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notice_file_no, name, ex, sizes, src);
  }
  
}
