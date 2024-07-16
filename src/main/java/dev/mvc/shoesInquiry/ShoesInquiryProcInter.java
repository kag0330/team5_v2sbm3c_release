package dev.mvc.shoesInquiry;

import java.util.ArrayList;

public interface ShoesInquiryProcInter {

  public int create(String title, String contents, int shoesno, int memberno);

  public int list_search_count(String word);

  public ArrayList<ShoesInquiryInfoVO> list_search_paging(String word, int now_page, int record_per_page);
  
  public ShoesInquiryInfoVO read(int shoes_inquiry_no);
  
  public int answer(int shoes_inquiry_no, char answer_visible, String answer_contents);
  
  public String pagingBox(int now_page, String word, String list_file, int search_count, 
      int record_per_page, int page_per_block);
  
  public int myInquiryCount(int memberno);
  
  public ArrayList<ShoesInquiryInfoVO> myInquiry(int memberno, int now_page, int record_per_page);
}
