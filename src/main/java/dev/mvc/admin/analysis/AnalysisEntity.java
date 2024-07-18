package dev.mvc.admin.analysis;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ANALYSIS")
public class AnalysisEntity {
  @Id
  @Column(name = "ANALYSISNO")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="analysis_seq")
  @SequenceGenerator(name="analysis_seq", sequenceName = "ANALYSIS_SEQ", allocationSize = 1)
  private Integer analysisno;
  
  /** 분석된 데이터 고유 분류 코드*/
  @Column(name = "CODE")
  private int code;
  
  /** 분석 제목 */
  @Column(name = "TITLE")
  private String title;
  
  /** 분석 내용*/
  @Column(name = "CONTENT")
  private String content;
  
  /** 분류, Chart 종류 */
  @Column(name = "CHART")
  private int chart;
  
  /** 차트 데이터 */
  @Column(name = "DATA", length = 4000)
  private String data;
  
  /** X label */
  @Column(name = "X_LABEL")
  private String xlabel;
  
  /** Y label */
  @Column(name = "Y_LABEL")
  private String ylabel;
  
  /** 범례 */
  @Column(name = "LEGNED")
  private String legend;
  
  /** 조회수*/
  @Column(name = "CNT")
  private int cnt;
    
  /** 등록 날짜*/
  @Column(name = "RDATE")
  private Date rdate;
  
  /*
  //Builder Pattern 예시
  AnalysisEntity analysisEntity = new AnalysisEntityBuilder()
      .analysisno(1)
      .code(1)
      .title("title")
      .content("content")
      .chart(1)
      .data("data")
      .xlabel("xlabel")
      .ylabel("ylabel")
      .legend("legend")
      .cnt(1)
      .rdate(new Date())
      .build();
  */
}
