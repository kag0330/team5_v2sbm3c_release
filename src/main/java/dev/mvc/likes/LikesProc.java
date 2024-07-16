package dev.mvc.likes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.likes.LikesProc")
public class LikesProc implements LikesProcInter{
  @Autowired
  private LikesDAOInter likesDAO;

  @Override
  public int increased_likes(int reviewno, int memberno) {
    Map<String, Object> map = new HashMap<>();
    map.put("reviewno", reviewno);
    map.put("memberno", memberno);
    System.out.println("reviewno" + reviewno);
    System.out.println("memberno" + memberno);
    int cnt = this.likesDAO.increased_likes(map);
    return cnt;
  }


  @Override
  public int decreased_likes(int reviewno, int memberno) {
    Map<String, Object> map = new HashMap<>();
    map.put("reviewno", reviewno);
    map.put("memberno", memberno);
    
    int cnt = this.likesDAO.decreased_likes(map);
    return cnt;
  }
  
  @Override
  public int delete(int reviewno) {
    int cnt = this.likesDAO.delete(reviewno);
    return cnt;
  }


  @Override
  public int likes_count(int reviewno) {
    int cnt = this.likesDAO.likes_count(reviewno);
    return cnt;
  }


  @Override
  public int mylikes(int reviewno, int memberno) {
    Map<String, Object> map = new HashMap<>();
    map.put("reviewno", reviewno);
    map.put("memberno", memberno);
    
    int cnt = this.likesDAO.mylikes(map);
    return cnt;
  }

}
