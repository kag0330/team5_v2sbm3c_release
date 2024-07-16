package dev.mvc.category;

import java.util.ArrayList;

public interface CategoryProcInter {

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
  
  public ArrayList<CategoryVO> list_search_paging(String word, int now_page, int record_per_page);
  
  public int list_search_count(String word);
  
  public String pagingBox(int now_page, String word, String list_file, int search_count, 
      int record_per_page, int page_per_block);
  
  public CategoryVO category_select (int categoryno);
  
  public ArrayList<CategoryVO> select_name(ArrayList<Integer> categorynoList);
  
  public ArrayList<CategoryVO> select_subname (int categoryno);

}
