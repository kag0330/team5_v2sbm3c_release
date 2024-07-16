package dev.mvc.reportType;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.reportType.ReportTypeProc")
public class ReportTypeProc implements ReportTypeProcInter{
  @Autowired
  private ReportTypeDAOInter reportTypeDAO;

  @Override
  public ArrayList<ReportTypeVO> search_type() {
    ArrayList<ReportTypeVO> reportType = this.reportTypeDAO.search_type();
    return reportType;
  }
  
  
}
