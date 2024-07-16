package dev.mvc.report;

import java.util.ArrayList;

public interface ReportProcInter {

  public int create(ReportVO reportVO);

  public int report_count(int memberno, int reviewno);

  public int list_search_count(String word);

  public ArrayList<ReportInfoVO> list_search_paging(String word, int now_page, int record_per_page);

  public ReportInfoVO read(int reportno);

  public int answer(int reportno, char report_visible, String report_contents);

  public String pagingBox(int now_page, String word, String list_file, int search_count, int record_per_page,
      int page_per_block);

  public int myReportCount(int memberno);

  public ArrayList<ReportInfoVO> myReport(int memberno);
}
