package dev.mvc.basket;

import java.util.ArrayList;
import java.util.Map;

import dev.mvc.option.OptionVO;
import dev.mvc.shoes.ShoesAllVO;

public interface BasketDAOInter {

  public ArrayList<ShoesAllVO> list(Map<String, Object> map);
  
  public Map<String, Object> read(Map<String, Object> map);
  
  
  public int create(Map<String, Object> map);
  
  
  public int update(Map<String, Object> map);
  
  public int delete(Map<String, Object> map);
 
  public int decrease(OptionVO optionVO);
  
  public int increase(OptionVO optionVO);
}
