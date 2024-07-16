package dev.mvc.option;

import java.util.ArrayList;
import java.util.Map;

public interface OptionDAOInter {

  public int option_create(OptionVO optionVO);
  
  public ArrayList<OptionVO> option_list(int shoesno);
  
  public int option_update(Map<String, Object> map);
  
  /**id="option_update_amount" parameterType="Map"*/
  public int option_update_amount(Map<String,Object> map);

  public int option_delete(int optionno);

  public int option_search_count(int shoesno);

  public ArrayList<OptionVO> option_paging(Map<String, Object> map);
  
  public ArrayList<Integer> option_sizes(int shoesno);

  public ArrayList<String> option_color(int shoesno);

  /**id="optionByshoesno" parameterType="int" resultType="dev.mvc.option.OptionVO"*/
  public ArrayList<OptionVO> optionByshoesno(int shoesno);

  public ArrayList<OptionVO> option_coloramount(Map<String, Object> map);

}
