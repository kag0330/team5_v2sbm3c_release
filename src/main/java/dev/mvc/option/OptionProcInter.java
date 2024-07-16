package dev.mvc.option;

import java.util.ArrayList;
import java.util.Map;

import dev.mvc.category.CategoryVO;

public interface OptionProcInter {

  public int option_create(OptionVO optionVO);

  public ArrayList<OptionVO> option_list(int shoesno);
  
  public int option_update(int amount, int optionno);

  public int option_delete(int optionno);

  public ArrayList<OptionVO> option_paging(int shoesno, String word, int now_page, int record_per_page);
  
  public int option_search_count(int shoesno);
  
  public ArrayList<Integer> option_sizes(int shoesno);
 
  public ArrayList<String> option_color(int shoesno);
  
  /**id="optionByshoesno" parameterType="int" resultType="dev.mvc.option.OptionVO"*/
  public ArrayList<OptionVO> optionByshoesno(int shoesno);

  public ArrayList<OptionVO> option_coloramount(int shoesno, int sizes);

}
