package dev.mvc.reportType;

import java.util.ArrayList;

public interface ReportTypeDAOInter {
  /**
   * id="search_type" parameterType="dev.mvc.reportType.ReportTypeVO"
   * @return 신고 분류
   */
  public ArrayList<ReportTypeVO> search_type();
}
