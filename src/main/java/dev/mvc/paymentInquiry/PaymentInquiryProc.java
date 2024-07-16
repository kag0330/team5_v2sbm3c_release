package dev.mvc.paymentInquiry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.paymentInquiry.PaymentInquiryProc")
public class PaymentInquiryProc implements PaymentInquiryProcInter{

  @Autowired
  private PaymentInquiryDAOInter paymentInquiryDAO;

  @Override
  public int create(String title, String contents, int memberno, int payment_details_no) {
    PaymentInquiryVO paymentInquiryVO = new PaymentInquiryVO();
    paymentInquiryVO.setTitle(title);
    paymentInquiryVO.setContents(contents);
    paymentInquiryVO.setMemberno(memberno);
    paymentInquiryVO.setPayment_details_no(payment_details_no);
    
    int cnt = this.paymentInquiryDAO.create(paymentInquiryVO);
    return cnt;
  }

  @Override
  public int list_search_count(String word) {
    int cnt = this.paymentInquiryDAO.list_search_count(word);
    return cnt;
  }

  @Override
  public ArrayList<PaymentInquiryInfoVO> list_search_paging(String word, int now_page, int record_per_page) {
    int begin_of_page = (now_page - 1) * record_per_page;
    int start_num = begin_of_page + 1;

    int end_num = begin_of_page + record_per_page;

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("word", word);
    map.put("start_num", start_num);
    map.put("end_num", end_num);
    
    ArrayList<PaymentInquiryInfoVO> list = this.paymentInquiryDAO.list_search_paging(map);
    return list;
  }

  @Override
  public String pagingBox(int now_page, String word, String list_file, int search_count, int record_per_page,
      int page_per_block) {
    int total_page = (int) (Math.ceil((double) search_count / record_per_page));
    int total_grp = (int) (Math.ceil((double) total_page / page_per_block));
    int now_grp = (int) (Math.ceil((double) now_page / page_per_block));
    int start_page = ((now_grp - 1) * page_per_block) + 1;
    int end_page = (now_grp * page_per_block);
    StringBuffer str = new StringBuffer();

    str.append("<style type='text/css'>");
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}");
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}");
    str.append("  #paging A:hover{text-decoration:none; background-color: #FFFFFF; color:black; font-size: 1em;}");
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}");
    str.append("  .span_box_1{");
    str.append("    text-align: center;");
    str.append("    font-size: 1em;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("  }");
    str.append("  .span_box_2{");
    str.append("    text-align: center;");
    str.append("    background-color: #668db4;");
    str.append("    color: #FFFFFF;");
    str.append("    font-size: 1em;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:1px 6px 1px 6px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("    margin:1px 2px 1px 2px; /*위, 오른쪽, 아래, 왼쪽*/");
    str.append("  }");
    str.append("</style>");
    str.append("<DIV id='paging'>");

    int _now_page = (now_grp - 1) * page_per_block;
    if (now_grp >= 2) {
      str.append("<span class='span_box_1'><A href='" + list_file + "?word=" + word + "&now_page=" + _now_page
          + "'>이전</A></span>");
    }

    for (int i = start_page; i <= end_page; i++) {
      if (i > total_page) {
        break;
      }

      if (now_page == i) {
        str.append("<span class='span_box_2'>" + i + "</span>");
      } else {
        str.append("<span class='span_box_1'><A href='" + list_file + "?word=" + word + "&now_page=" + i + "'>" + i
            + "</A></span>");
      }
    }

    _now_page = (now_grp * page_per_block) + 1; // 최대 페이지수 + 1
    if (now_grp < total_grp) {
      str.append("<span class='span_box_1'><A href='" + list_file + "?word=" + word + "&now_page=" + _now_page
          + "'>다음</A></span>");
    }
    str.append("</DIV>");

    return str.toString();
  }
  
  @Override
  public PaymentInquiryInfoVO read(int payment_inquiry_no) {
    PaymentInquiryInfoVO paymentInquiryInfoVO = this.paymentInquiryDAO.read(payment_inquiry_no);
    return paymentInquiryInfoVO;
  }
  
  @Override
  public int answer(int payment_inquiry_no, char answer_visible, String answer_contents) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("payment_inquiry_no", payment_inquiry_no);
    map.put("answer_visible", answer_visible);
    map.put("answer_contents", answer_contents);
    
    int cnt = this.paymentInquiryDAO.answer(map);
    return cnt;
  }

  @Override
  public ArrayList<PaymentInquiryInfoVO> inquiry_select(int memberno) {
    ArrayList<PaymentInquiryInfoVO> list = this.paymentInquiryDAO.inquiry_select(memberno);
    return list;
  }

  @Override
  public int myInquiryCount(int memberno) {
    int cnt = this.paymentInquiryDAO.myInquiryCount(memberno);
    return cnt;
  }

  @Override
  public ArrayList<PaymentInquiryInfoVO> myInquiry(int memberno, int now_page, int record_per_page) {
    int begin_of_page = (now_page - 1) * record_per_page;

    int start_num = begin_of_page + 1;
    int end_num = begin_of_page + record_per_page;
    
    Map <String, Object> map = new HashMap<>();
    map.put("memberno", memberno);
    map.put("start_num", start_num);
    map.put("end_num", end_num);
    
    ArrayList<PaymentInquiryInfoVO> paymentInquiryInfoVO = this.paymentInquiryDAO.myInquiry(map);
    return paymentInquiryInfoVO;
  }
}
