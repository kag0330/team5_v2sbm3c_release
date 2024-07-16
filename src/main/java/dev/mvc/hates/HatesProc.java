package dev.mvc.hates;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.hates.HatesProc")
public class HatesProc implements HatesProcInter{
  @Autowired
  private HatesDAOInter hatesDAO;

  @Override
  public int increased_hates(int reviewno, int memberno) {
    Map<String, Object> map = new HashMap<>();
    map.put("reviewno", reviewno);
    map.put("memberno", memberno);
    
    int cnt = this.hatesDAO.increased_hates(map);
    return cnt;
  }


  @Override
  public int decreased_hates(int reviewno, int memberno) {
    Map<String, Object> map = new HashMap<>();
    map.put("reviewno", reviewno);
    map.put("memberno", memberno);
    
    int cnt = this.hatesDAO.decreased_hates(map);
    return cnt;
  }
  
  @Override
  public int delete(int reviewno) {
    int cnt = this.hatesDAO.delete(reviewno);
    return cnt;
  }


  @Override
  public int hates_count(int reviewno) {
    int cnt = this.hatesDAO.hates_count(reviewno);
    return cnt;
  }


  @Override
  public int myhates(int reviewno, int memberno) {
    Map<String, Object> map = new HashMap<>();
    map.put("reviewno", reviewno);
    map.put("memberno", memberno);
    
    int cnt = this.hatesDAO.myhates(map);
    return cnt;
  }

}
