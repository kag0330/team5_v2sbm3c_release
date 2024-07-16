package dev.mvc.basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mvc.option.OptionVO;
import dev.mvc.shoes.ShoesAllVO;

//@Component("dev.mvc.shoes.ShoesProc")
@Service("dev.mvc.basket.BasketProc")
public class BasketProc implements BasketProcInter {
  @Autowired
  private BasketDAOInter basketDAO;

  public BasketProc() {
    // System.out.println("-> ShoesProc created.");
  }

  @Override
  public ArrayList<ShoesAllVO> list(int memberno) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("memberno", memberno);
    
    ArrayList<ShoesAllVO> list = this.basketDAO.list(map);
    return list;
  }
  
  @Override
  public Map<String, Object> read(Map<String, Object> map) {
    Map<String, Object> result = this.basketDAO.read(map);
    System.out.println(map.toString());
    if(result == null || result.isEmpty()) {
      return null;
    }
    
    return this.basketDAO.read(map);
  }

  @Override
  public int create(int memberno, String color, int sizes, int amount) {
    Map<String, Object> map = new HashMap<>();
    map.put("memberno", memberno);
    map.put("color", color);
    map.put("sizes", sizes);
    map.put("amount", amount);
    
    int cnt = this.basketDAO.create(map);
    System.out.println("cnt: "+cnt);
    return cnt;
  }

  @Override
  public int update(int amount, int memberno, int basketno) {
    Map<String, Object> map = new HashMap<>();
    map.put("amount", amount);
    map.put("memberno", memberno);
    map.put("basketno", basketno);
    int cnt = this.basketDAO.update(map);
    return cnt;
  }

  @Override
  public int delete(Integer memberno, Integer basketno) {
    Map<String, Object> map = new HashMap<>();
    
    map.put("memberno", memberno);
    map.put("basketno", basketno);
    
    int cnt = this.basketDAO.delete(map);
    return cnt;
  }
  @Override
  public int decrease(OptionVO optionVO) {
    int cnt = this.basketDAO.decrease(optionVO);
    return cnt;
  }
  
  @Override
  public int increase(OptionVO optionVO) {
    int cnt = this.basketDAO.increase(optionVO);
    return cnt;
  }

  
}

