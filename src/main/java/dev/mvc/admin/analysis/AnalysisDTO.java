package dev.mvc.admin.analysis;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnalysisDTO {
  
  private Integer analysisno;
  
  /** 분석된 데이터 고유 분류 코드*/
  private int code;
  
  /** 분석 제목 */
  private String title;
  
  /** 분석 내용*/
  private String content;
  
  /** 분류, Chart 종류 */
  private int chart;
  
  /** 차트 데이터 */
  private String data;
  
  /** X label */
  private String xlabel;
  
  /** Y label */
  private String ylabel;
  
  /** 범례 */
  private String legend;
  
  /** 조회수*/
  private int cnt;
    
  /** 등록 날짜*/
  private Date rdate;
  

}
