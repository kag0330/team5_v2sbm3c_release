package dev.mvc.basket;

import java.util.ArrayList;
import java.util.Map;

import dev.mvc.option.OptionVO;
import dev.mvc.shoes.ShoesAllVO;

public interface BasketProcInter {

  public ArrayList<ShoesAllVO> list(int memberno);
  
  public Map<String, Object> read(Map<String, Object> map);
  
  public int create(int memberno, String color, int sizes, int amount, int shoesno);

  
  public int delete(Integer memberno, Integer basketno);


  public int update(int amount, int memberno, int basketno);

  public int decrease(OptionVO optionVO);
  
  public int increase(OptionVO optionVO);
}
