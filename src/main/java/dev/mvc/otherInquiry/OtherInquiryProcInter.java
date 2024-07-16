package dev.mvc.otherInquiry;

import java.util.ArrayList;


public interface OtherInquiryProcInter {

  public int create(String title, String contents, int memberno);

  public int list_search_count(String word);

  public ArrayList<OtherInquiryInfoVO> list_search_paging(String word, int now_page, int record_per_page);

  public OtherInquiryInfoVO read(int other_inquiry_no);

  public int answer(int other_inquiry_no, char answer_visible, String answer_contents);

  public String pagingBox(int now_page, String word, String list_file, int search_count, int record_per_page,
      int page_per_block);
  
  public int myInquiryCount(int memberno);
  
  public ArrayList<OtherInquiryVO> myInquiry(int memberno, int now_page, int record_per_page);
}
