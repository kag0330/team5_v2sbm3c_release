package dev.mvc.paymentInquiry;

import java.util.ArrayList;

public interface PaymentInquiryProcInter {

  public int create(String title, String contents, int memberno, int payment_details_no);

  public int list_search_count(String word);

  public ArrayList<PaymentInquiryInfoVO> list_search_paging(String word, int now_page, int record_per_page);

  public PaymentInquiryInfoVO read(int payment_inquiry_no);

  public int answer(int payment_inquiry_no, char answer_visible, String answer_contents);

  public String pagingBox(int now_page, String word, String list_file, int search_count, int record_per_page,
      int page_per_block);
  
  public ArrayList<PaymentInquiryInfoVO> inquiry_select(int memberno);
  
  public int myInquiryCount(int memberno);
  
  public ArrayList<PaymentInquiryInfoVO> myInquiry(int memberno, int now_page, int record_per_page);
}
