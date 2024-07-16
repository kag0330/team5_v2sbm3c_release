package dev.mvc.category;

import java.util.ArrayList;
import java.util.Map;

public interface CategoryDAOInter {
  
  public ArrayList<String> name_list();
  
  public int parent_count(int categoryno);
  
  public int parent_categoryno(String name);
  
  public ArrayList<String> subname_list(int categoryno);
  
  public int create(CategoryVO categoryVO);
  
  public ArrayList<CategoryVO> list_all();
  
  public CategoryVO read(int categoryno);
  
  public int update(CategoryVO categoryVO);
  
  public int delete(int categoryno);
  
  public ArrayList<CategoryVO> list_all_name();
  
  public ArrayList<CategoryVO> list_all_subname(String name);
  
  public ArrayList<CategoryVO> list_search(String word);
  
  public ArrayList<CategoryVO> list_search_paging(Map<String, Object> map);
  
  public int list_search_count(String word);

  public CategoryVO category_select(int categoryno);
  
  public ArrayList<CategoryVO> select_name(ArrayList<Integer> categorynoList);
  
  public ArrayList<CategoryVO> select_subname(int categoryno);
  
}
