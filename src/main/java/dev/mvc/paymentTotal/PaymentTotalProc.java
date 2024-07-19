package dev.mvc.paymentTotal;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.mvc.option.OptionDAOInter;
import dev.mvc.payment.PaymentDAOInter;
import dev.mvc.payment.PaymentProc;
import dev.mvc.paymentDetails.PaymentDetailsDAOInter;
import dev.mvc.paymentDetails.PaymentDetailsVO;
import dev.mvc.shoes.ShoesDAOInter;
import dev.mvc.tool.Tool;

@Service("dev.mvc.paymentTotal.PaymentTotalProc")
public class PaymentTotalProc implements PaymentTotalProcInter {
  @Autowired
  private PaymentTotalDAOInter paymentTotalDAO;
  
  @Autowired
  private PaymentDAOInter paymentDAO;
  
  @Autowired
  private PaymentProc paymentProc;
  
  @Autowired
  private PaymentDetailsDAOInter paymentDetailsDAO;
  
  @Autowired
  private ShoesDAOInter shoesDAO;
  
  @Autowired
  private OptionDAOInter optionDAO;

  @Autowired
  private SqlSession sqlsession;

  @Override
  public ArrayList<PaymentTotalVO> list(int memberno, int date, String search) {
    if (date > 0) {
      date = -date;
    }
    Date today = new Date();
    Date startDate = Tool.addDays(today, date);
    
    Calendar cal = Calendar.getInstance();
    cal.setTime(startDate);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    startDate = cal.getTime();
    
    HashMap<String, Object> map = new HashMap<>();
    map.put("memberno", memberno);
    map.put("startDate", startDate);
    map.put("endDate", today);
    map.put("search", search);
    
    List<PaymentTotalVO> paymentTotalList = new ArrayList<>();

    sqlsession.select("dev.mvc.paymentTotal.PaymentTotalDAOInter.list", map,
        (ResultHandler<PaymentTotalVO>) context -> {
          PaymentTotalVO paymentTotal = context.getResultObject();

          HashMap<String, Object> childMap = new HashMap<>();
          childMap.put("paymentno", paymentTotal.getPaymentno());
          childMap.put("search", search);

          List<PaymentDetailsOptionVO> paymentDetailsList = sqlsession
              .selectList("dev.mvc.paymentTotal.PaymentTotalDAOInter.selectPaymentDetailsByPaymentNo", childMap);
          
          // 중복 제거를 위한 Set 사용
          Set<Integer> seenDetails = new HashSet<>();
          ArrayList<PaymentDetailsOptionVO> uniquePaymentDetails = new ArrayList<>();
          for (PaymentDetailsOptionVO details : paymentDetailsList) {
            if (!seenDetails.contains(details.getPayment_details_no())) {
              seenDetails.add(details.getPayment_details_no());
              uniquePaymentDetails.add(details);
            }
          }

          if (!uniquePaymentDetails.isEmpty()) {
            paymentTotal.setPayment_details_option(uniquePaymentDetails);
            paymentTotalList.add(paymentTotal);
          }
        });

    return (ArrayList<PaymentTotalVO>) paymentTotalList;
  }



  @Override
  public ArrayList<PaymentTotalVO> cslist(int memberno, int date, String search) {
    if (date > 0) {
      date = -date;
    }
    Date today = new Date();
    Date dates = Tool.addDays(today, date);

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("memberno", memberno);
    map.put("startDate", dates);
    map.put("endDate", today);
    map.put("search", search);

    List<PaymentTotalVO> paymentTotalList = new ArrayList<>();

    sqlsession.select("dev.mvc.paymentTotal.PaymentTotalDAOInter.cslist", map, (ResultHandler<PaymentTotalVO>) context -> {
      PaymentTotalVO paymentTotal = context.getResultObject();

      HashMap<String, Object> childMap = new HashMap<>();
      childMap.put("paymentno", paymentTotal.getPaymentno());
      childMap.put("search", search);

      ArrayList<PaymentDetailsOptionVO> paymentDetailsList = (ArrayList) sqlsession.selectList("dev.mvc.paymentTotal.PaymentTotalDAOInter.selectPaymentDetailsByPaymentNo", childMap);

      if (!paymentDetailsList.isEmpty()) {
        paymentTotal.setPayment_details_option(paymentDetailsList);
        paymentTotalList.add(paymentTotal);
      }
    });

    return (ArrayList<PaymentTotalVO>) paymentTotalList;
  }



  @Override
  public ArrayList<PaymentTotalVO> listAdminPaging(String word, int now_page, int record_per_page, Map<String,Object> map) {
    int begin_of_page = (now_page - 1) * record_per_page;
    int start_num = begin_of_page + 1;
    int end_num = begin_of_page + record_per_page;
    
    map.put("word", word);
    map.put("start_num", start_num);
    map.put("end_num", end_num);

    return this.paymentTotalDAO.listAdminPaging(map);
  }

  @Override
  public ArrayList<PaymentTotalVO> listAdminPDO(Map<String, Object> map){
    return this.paymentTotalDAO.listAdminPDO(map);
  };

  @Override
  public String paymentAjax(Map<String,Object> map) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    StringBuffer str = new StringBuffer();
    
    String now_page_str =  map.get("now_page").toString();
    int now_page = now_page_str.isEmpty() ? 1 : Integer.parseInt(now_page_str);
 
    ArrayList<PaymentTotalVO> paymentTotalList =  this.listAdminPaging(map.get("word").toString(), now_page, PaymentTotalVO.RECORD_PER_PAGE, map);
    for(PaymentTotalVO paymentTotal : paymentTotalList) {
      str.append("<div>");
      str.append("  <button type=\"button\" class=\"collapsible\" onclick=\"fetchList(this, " + paymentTotal.getMemberno() + ");\">");
      str.append("  <div style=\"display: flex; justify-content: space-around;\">");
      str.append("    <span class=\"color-white\" >회원번호:" + paymentTotal.getMemberno() + "</span>");
      str.append("    <span class=\"color-white\" >회원ID: " + paymentTotal.getMemberid() + "</span>");
      str.append("    <span class=\"color-white\" >회원닉네임: " + paymentTotal.getNickname() + " </span>");
      str.append("    <span class=\"color-white\" >마지막 주문일자: " + sdf.format(paymentTotal.getRdate()) + " </span>");
      str.append("  </div>");
      str.append("</button>");
      str.append("<div class=\"contents\" id=\"btnDiv\"></div>");
      str.append("</div>");
    }
    str.append("<div class=\"bottom_menu\">" + this.paymentProc.pagingBox(now_page, map.get("word").toString(), map.get("path").toString(), this.paymentTotalDAO.member_cnt(map)) + "</div>");
    
    return str.toString();
  }

  @Override
  public String paymentDetailsAjax(int memberno, Map<String,Object> map) {
    StringBuffer str = new StringBuffer();
    
    map.put("memberno", memberno);
    
    ArrayList<PaymentTotalVO> list = this.listAdminPDO(map);
    
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DecimalFormat df = new DecimalFormat("###,###원");

    String[] payment_statuses = { "입금전", "추가입금대기", "입금완료(수동)", "입금완료(자동)", "결제완료" };
    String[] statuses = { "상품준비중", "배송준비중", "배송보류", "배송대기", "배송중", "배송완료" };
    String[] cs_statuses = { "", "취소", "교환", "반품", "환불" };

    for (PaymentTotalVO payment : list) {

      String current_payment_status = payment.getPayment_status();
      String current_status = payment.getStatus();
      String current_cs_status = payment.getCs_status();

      str.append(
          "  <button type=\"button\" class=\"collapsible\" onclick=\"collapseChild(this);\" style=\"padding: 5px;\">");
      str.append("    <div style=\"padding: 5px 30px 5px 30px;\">");
      str.append("      <div style=\"float: left;\">");
      str.append("        <span class=\"color-white block\" >주문번호: " + payment.getPaymentno() + "</span>");
      for (PaymentDetailsOptionVO pdo : payment.getPayment_details_option()) {
        str.append("        <div>");
        str.append("          <span class=\"color-white\" style=\"width: 120px; float: left;\">주문상세번호: "
            + pdo.getPayment_details_no() + "</span>");
        str.append("          <span class=\"color-white\">상 품 명:" + pdo.getTitle() + " </span>");
        str.append("        </div>");
      }
      str.append(
          "        <span class=\"color-white block\">주문일자: " + simpleDateFormat.format(payment.getRdate()) + "</span>");
      str.append("      </div>");
      str.append("      <div style=\"float: right;\">");
      str.append("        <span class=\"color-white block\">결제상태: " + payment.getPayment_status() + "</span>");

      str.append("        <span class=\"color-white block\">주문상태: " + payment.getStatus() + "</span>");
      str.append("        <span class=\"color-white block\">C S 상태: "
          + (payment.getCs_status() != null ? payment.getCs_status() : "NULL") + "</span>");
      str.append("      </div>");
      str.append("    </div>");
      str.append("  </button>");

      str.append("  <div class=\"contents\">");
      str.append("    <div class=\"clearfix\" style=\"margin-top: 10px;\">");
      str.append("      <div>");
      str.append("        <span>총 상품가격: " + df.format(payment.getTotal_price()) + "&nbsp;&nbsp;&nbsp;&nbsp;" + "배송비: "
          + df.format(payment.getDelivery()) + "&nbsp;&nbsp;&nbsp;&nbsp;" + " 총 주문가격: "
          + df.format(payment.getTotal_payment()) + "</span>");
      str.append("      </div>");
      str.append("      <div style=\"float: left;\">");
      str.append("        <div style=\"display: flex; align-items: center;\">");
      str.append("          <label style=\"width: 120px; float: left;\">결제상태 수정하기</label>");
      str.append("          <select style=\"height: 2rem; width: 10rem;\" name=\"payment_status\" id=\"selectbox\">");

      for (String status : payment_statuses) {
        if (status.equals(current_payment_status)) {
          str.append(String.format("            <option selected>%s</option>", status));
        } else {
          str.append(String.format("            <option>%s</option>", status));
        }
      }

      str.append("          </select>");
      str.append("        </div>");

      str.append("        <div style=\"display: flex; align-items: center;\">");
      str.append("          <label style=\"width: 120px; float: left;\">주문상태 수정하기</label>");
      str.append("          <select style=\"height: 2rem; width: 10rem;\" name=\"status\" id=\"selectbox\">");

      for (String status : statuses) {
        if (status.equals(current_status)) {
          str.append(String.format("            <option selected>%s</option>", status));
        } else {
          str.append(String.format("            <option>%s</option>", status));
        }
      }

      str.append("          </select>");
      str.append("        </div>");

      str.append("        <div style=\"display: flex; align-items: center;\">");
      str.append("          <label style=\"width: 120px; float: left;\">CS상태 수정하기</label>");
      str.append("          <select style=\"height: 2rem; width: 10rem;\" name=\"cs_status\" id=\"selectbox\">");

      for (String status : cs_statuses) {
        if (status.equals("")) {
          if (current_cs_status == null || current_cs_status == "") {
            str.append("            <option selected value=\"NULL\">NULL로 변경</option>");
          } else {
            str.append("            <option value=\"NULL\">NULL로 변경</option>");
          }
        } else if (status.equals(current_cs_status)) {
          str.append(String.format("            <option selected>%s</option>", status));
        } else {
          str.append(String.format("            <option>%s</option>", status));
        }
      }

      str.append("          </select>");
      str.append("        </div>");
      str.append("      </div>");

      str.append("      <div style=\"float:right;\">");
      str.append(
          "        <button style=\"position: relative; bottom: 0px; right: 0px; height: 56px; width: 8rem; border: 1px solid #bbb; background-color: #e63740; color:#fff\" onclick=\"submit(this)\" type=\"button\">적용하기</button>");
      str.append("      </div>");
      str.append("    </div>");

      str.append("    <!--/*주문 정보 기입란*/-->");
      str.append("    <hr>");
      for (PaymentDetailsOptionVO pdo : payment.getPayment_details_option()) {
        str.append("    <div>");
        str.append("      <table style=\"width: 100%;\">");
        str.append("        <colgroup>");
        str.append("          <col style=\"width: 33%;\" />");
        str.append("          <col style=\"width: 33%;\" />");
        str.append("          <col style=\"width: 33%;\" />");
        str.append("        </colgroup>");
        str.append("        <tbody>");
        str.append("          <tr>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">신발번호:</span>");
        str.append("                <span>" + pdo.getShoesno() + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">신발평점:</span>");
        str.append("                <span>" + pdo.getRating() + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">상 품 명:</span>");
        str.append("                <span>" + pdo.getTitle() + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("          </tr>");

        str.append("          <tr>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">주문상세번호:</span>");
        str.append("                <span>" + pdo.getPayment_details_no() + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">할 인 률:</span>");
        str.append("                <span>" + String.format("%.1f", pdo.getDiscount()) + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">브랜드명:</span>");
        str.append("                <span>" + pdo.getBrand() + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("          </tr>");

        str.append("          <tr>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">주문수량:</span>");
        str.append("                <span>" + pdo.getPayment_amount() + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">신발가격:</span>");
        str.append("                <span>" + df.format(pdo.getPrice()) + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("            <td>");
        str.append("              <div>");
        str.append("                <span style=\"float: left; width: 100px;\">사이즈 / 색상:</span>");
        str.append("                <span th:text=\"|${pdo.sizes} / ${pdo.color}|\">" + pdo.getSizes() + " / "
            + pdo.getColor() + "</span>");
        str.append("              </div>");
        str.append("            </td>");
        str.append("          </tr>");
        str.append("        </tbody>");
        str.append("      </table>");
        str.append("      <hr>");
        str.append("    </div>");
      }
      str.append("  </div>");
    }
    return str.toString();

  }


  @Override
  @Transactional
  public boolean create(Map<String, Object> map) {
    List<Map<String,Object>> basketList = (List<Map<String, Object>>) map.get("basket");
    int total_price = basketList.stream().mapToInt(item->(int)item.get("price")).sum();

    
    map.put("total_price", total_price);
    map.put("delivery", (total_price >= 100000 ?  0 : 2500) );
    
    if(this.paymentDAO.create(map) < 1)//total_price, delivery, memberno
      return false;
    
    for(Map<String,Object> basket: basketList) {
      basket.put("paymentno", map.get("paymentno"));
      
      if(this.optionDAO.option_update_amount(basket) < 1)//amount, optionno
        return false;
      
      if(this.paymentDetailsDAO.create(basket) < 1)//amount, paymentno, optionno
        return false;
    }
    
    return true;
  }

  @Override
  public int member_cnt(Map<String, Object> map) {
    return this.paymentTotalDAO.member_cnt(map);
  }

  @Override
  public int payment_cnt(Map<String, Object> map) {
    return this.paymentTotalDAO.payment_cnt(map);
  }

}
