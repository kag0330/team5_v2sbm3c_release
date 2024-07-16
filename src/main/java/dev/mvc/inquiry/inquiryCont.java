package dev.mvc.inquiry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.member.MemberVO;
import dev.mvc.otherInquiry.OtherInquiryProcInter;
import dev.mvc.otherInquiry.OtherInquiryVO;
import dev.mvc.paymentInquiry.PaymentInquiryInfoVO;
import dev.mvc.paymentInquiry.PaymentInquiryProcInter;
import dev.mvc.shoes.ShoesProcInter;
import dev.mvc.shoes.ShoesVO;
import dev.mvc.shoesInquiry.ShoesInquiryInfoVO;
import dev.mvc.shoesInquiry.ShoesInquiryProcInter;
import dev.mvc.team5.DefaultCont;
import dev.mvc.tool.Alert;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/inquiry")
@Controller
public class inquiryCont {

  @Autowired
  @Qualifier("dev.mvc.shoes.ShoesProc")
  private ShoesProcInter shoesProc;

  @Autowired
  @Qualifier("dev.mvc.shoesInquiry.ShoesInquiryProc")
  private ShoesInquiryProcInter shoesinquiryProc;

  @Autowired
  @Qualifier("dev.mvc.paymentInquiry.PaymentInquiryProc")
  private PaymentInquiryProcInter paymentinquiryProc;

  @Autowired
  @Qualifier("dev.mvc.otherInquiry.OtherInquiryProc")
  private OtherInquiryProcInter otherinquiryProc;

  public int record_per_page = 5;
  public int page_per_block = 5;

  @GetMapping(value = "/myInquiry/{type}")
  public String myInquiry(HttpSession session, Model model, @PathVariable("type") String type,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    if (memberVO != null) {
      model.addAttribute("type", type);
      int memberno = memberVO.getMemberno();
      if (type.equals("shoes")) {
        ArrayList<ShoesInquiryInfoVO> list = this.shoesinquiryProc.myInquiry(memberno, now_page, this.record_per_page);
        model.addAttribute("list", list);

        int search_count = this.shoesinquiryProc.myInquiryCount(memberno);

        String paging = this.shoesinquiryProc.pagingBox(now_page, "", "/inquiry/myInquiry/shoes", search_count,
            this.record_per_page, this.page_per_block);

        int no = search_count - ((now_page - 1) * this.record_per_page);

        model.addAttribute("paging", paging);
        model.addAttribute("now_page", now_page);
        model.addAttribute("no", no);
      }
      if (type.equals("payment")) {
        ArrayList<PaymentInquiryInfoVO> list = this.paymentinquiryProc.myInquiry(memberno, now_page,
            this.record_per_page);
        model.addAttribute("list", list);

        int search_count = this.paymentinquiryProc.myInquiryCount(memberno);

        String paging = this.paymentinquiryProc.pagingBox(now_page, "", "/inquiry/myInquiry/payment", search_count,
            this.record_per_page, this.page_per_block);

        int no = search_count - ((now_page - 1) * this.record_per_page);

        model.addAttribute("paging", paging);
        model.addAttribute("now_page", now_page);
        model.addAttribute("no", no);
      } else if (type.equals("other")) {
        ArrayList<OtherInquiryVO> list = this.otherinquiryProc.myInquiry(memberno, now_page, this.record_per_page);
        model.addAttribute("list", list);

        int search_count = this.otherinquiryProc.myInquiryCount(memberno);

        String paging = this.otherinquiryProc.pagingBox(now_page, "", "/inquiry/myInquiry/other", search_count,
            this.record_per_page, this.page_per_block);

        int no = search_count - ((now_page - 1) * this.record_per_page);
        
        model.addAttribute("paging", paging);
        model.addAttribute("now_page", now_page);
        model.addAttribute("no", no);
      }
    } else {
      Alert message = new Alert("로그인 후 이용해주세요.", "/login/signin", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
    return "/inquiry/myInquiry";
  }

  @PostMapping(value = "/select")
  @ResponseBody
  public Map<String, Object> select(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    Map<String, Object> response = new HashMap<>();
    String select = map.get("select");
    String word = map.get("word");

    if (memberVO != null) {
      int memberno = memberVO.getMemberno();
      switch (select) {
      case "shoes":
        ArrayList<ShoesVO> shoesVO = this.shoesProc.inquiry_select(word);
        response.put("list", shoesVO);
        if (shoesVO.size() == 0) {
          response.put("success", false);
        } else {
          response.put("success", true);
        }
        break;
      case "payment":
        ArrayList<PaymentInquiryInfoVO> paymentInquiryInfoVO = this.paymentinquiryProc.inquiry_select(memberno);
        response.put("list", paymentInquiryInfoVO);
        if (paymentInquiryInfoVO.size() == 0) {
          response.put("success", false);
        } else {
          response.put("success", true);
        }
        break;
      case "other":
        // 쿼리 실행
        break;
      default:
        break;
      }

    } else {
      response.put("nologin", true);
    }
    return response;
  }

  @PostMapping(value = "/shoes")
  @ResponseBody
  public Map<String, Object> shoes(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    Map<String, Object> response = new HashMap<>();
    if (memberVO != null) {
      int memberno = memberVO.getMemberno();
      int shoesno = Integer.parseInt(map.get("number"));
      String title = map.get("title");
      String contents = map.get("contents");

      int cnt = this.shoesinquiryProc.create(title, contents, shoesno, memberno);
      if (cnt == 1) {
        response.put("success", true);
      }
    } else {
      response.put("nologin", true);
    }

    return response;
  }

  @PostMapping(value = "/payment")
  @ResponseBody
  public Map<String, Object> payment(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    Map<String, Object> response = new HashMap<>();
    if (memberVO != null) {
      int memberno = memberVO.getMemberno();
      int payment_details_no = Integer.parseInt(map.get("number"));
      String title = map.get("title");
      String contents = map.get("contents");
      
      System.out.println("m"+ payment_details_no);
      System.out.println("m"+ title);
      System.out.println("m"+ contents);
      System.out.println("m"+ memberno);
      
      int cnt = this.paymentinquiryProc.create(title, contents, memberno, payment_details_no);
      if (cnt == 1) {
        response.put("success", true);
      }
    } else {
      response.put("nologin", true);
    }

    return response;
  }

  @PostMapping(value = "/other")
  @ResponseBody
  public Map<String, Object> other(HttpSession session, Model model, @RequestBody Map<String, String> map) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    Map<String, Object> response = new HashMap<>();
    if (memberVO != null) {
      int memberno = memberVO.getMemberno();
      String title = map.get("title");
      String contents = map.get("contents");

      int cnt = this.otherinquiryProc.create(title, contents, memberno);
      if (cnt == 1) {
        response.put("success", true);
      }
    } else {
      response.put("nologin", true);
    }

    return response;
  }
}
