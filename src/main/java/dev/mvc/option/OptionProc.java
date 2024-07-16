package dev.mvc.option;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.option.OptionProc")
public class OptionProc implements OptionProcInter {

  @Autowired
  private OptionDAOInter optionDAO;

  @Override
  public int option_create(OptionVO optionVO) {
    int cnt = this.optionDAO.option_create(optionVO);
    return cnt;
  }

  @Override
  public ArrayList<OptionVO> option_list(int shoesno) {
    ArrayList<OptionVO> optionVO = this.optionDAO.option_list(shoesno);
    return optionVO;
  }

  @Override
  public int option_update(int amount, int optionno) {
    Map<String, Object> map = new HashMap<>();
    map.put("amount", amount);
    map.put("optionno", optionno);
    int cnt = this.optionDAO.option_update(map);
    return cnt;
  }

  @Override
  public int option_delete(int optionno) {
    int cnt = this.optionDAO.option_delete(optionno);
    return cnt;
  }

  @Override
  public int option_search_count(int shoesno) {
    int cnt = this.optionDAO.option_search_count(shoesno);
    return cnt;
  }

  @Override
  public ArrayList<OptionVO> option_paging(int shoesno, String word, int now_page, int record_per_page) {
    int begin_of_page = (now_page - 1) * record_per_page;

    int start_num = begin_of_page + 1;

    int end_num = begin_of_page + record_per_page;

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("shoesno", shoesno);
    map.put("word", word);
    map.put("start_num", start_num);
    map.put("end_num", end_num);

    ArrayList<OptionVO> list = this.optionDAO.option_paging(map);
    return list;

  }

  @Override
  public ArrayList<Integer> option_sizes(int shoesno) {
    ArrayList<Integer> sizes = this.optionDAO.option_sizes(shoesno);
    return sizes;
  }

  @Override
  public ArrayList<OptionVO> option_coloramount(int shoesno, int sizes) {
    Map<String, Object> map = new HashMap<>();
    map.put("shoesno", shoesno);
    map.put("sizes", sizes);
    
    ArrayList<OptionVO> color = this.optionDAO.option_coloramount(map);
    return color;
  }

  @Override
  public ArrayList<OptionVO> optionByshoesno(int shoesno) {
    return this.optionDAO.optionByshoesno(shoesno);
  }

  @Override
  public ArrayList<String> option_color(int shoesno) {
    return this.optionDAO.option_color(shoesno);
  }
}
