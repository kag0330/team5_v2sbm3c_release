package dev.mvc.basket;

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
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.option.OptionVO;
import dev.mvc.payment.PaymentProc;
import dev.mvc.payment.PaymentProcInter;
import dev.mvc.paymentTotal.PaymentTotalProcInter;
import dev.mvc.shoes.ShoesAllVO;
import dev.mvc.shoes.ShoesProcInter;
import dev.mvc.shoes.ShoesVO;
import dev.mvc.shoesFile.ShoesFileProcInter;
import dev.mvc.team5.DefaultCont;
import dev.mvc.tool.Alert;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/basket")
@Controller
public class BasketCont {
  @Autowired
  @Qualifier("dev.mvc.shoes.ShoesProc")
  private ShoesProcInter shoesProc;

  @Autowired
  @Qualifier("dev.mvc.basket.BasketProc")
  private BasketProcInter basketProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc") // @Service("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.paymentTotal.PaymentTotalProc")
  private PaymentTotalProcInter paymentTotalProc;
  
  @Autowired
  @Qualifier("dev.mvc.shoesFile.ShoesFileProc")
  private ShoesFileProcInter shoesFileProc;

  /** 페이지당 출력할 레코드 갯수, nowPage는 1부터 시작 */
  public int record_per_page = 5;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public int page_per_block = 5;

  public BasketCont() {
    System.out.println("-> BasketCont created.");
  }

  private void table_paging(Model model, int memberno, String word, int now_page) {

    ArrayList<ShoesVO> list = this.shoesProc.list_search_paging(memberno, word);
    model.addAttribute("list", list);
    int search_count = this.shoesProc.list_search_count(memberno, word);

    int no = search_count - ((now_page - 1) * this.record_per_page);

    model.addAttribute("now_page", now_page);
    model.addAttribute("memberno", memberno);
    model.addAttribute("word", word);
    model.addAttribute("no", no);
  }

  /**
   * 장바구니
   * 
   * @param session
   * @param model
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/basket_list")
  public String basket_list(HttpSession session, Model model) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    if (memberVO != null) {
      int memberno = memberVO.getMemberno();
      
      ArrayList<ShoesAllVO> list = this.basketProc.list(memberno);
      
      model.addAttribute("list", list);
      model.addAttribute("shoesFileVO", this.shoesFileProc.list());
      return "basket/basket_list"; // /templates/basket/basket.html
    } else {
      Alert message = new Alert("로그인 후 이용해주세요.", "/login/signin", RequestMethod.GET, null);
      return DefaultCont.showMessageAndRedirect(message, model);
    }
  }
  
  @ResponseBody
  @PostMapping("basket_list/payment")
  public boolean basket_list_create(@RequestBody Map<String, Object> map) {
    System.out.println("basketmap: " + map.toString());
    if(!this.paymentTotalProc.create(map)) {
        return false;
    }
    int memberno = (int) map.get("memberno");
    this.basketProc.delete(memberno ,null);
    return true;
  }

  @PostMapping(value = "/create")
  @ResponseBody
  public Map<String, Object> create(@RequestBody Map<String, Object> map, HttpSession session) {
    
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    Map<String, Object> response = new HashMap<>();

    if (memberVO == null) {
      response.put("message", "로그인이 필요합니다.");
      return response;
    }
    
    int memberno = memberVO.getMemberno();
    String color = (String) map.get("color");
    int sizes = (Integer) map.get("sizes");
    int amount = (Integer) map.get("amount");
    
    Map<String, Object> readMap = this.basketProc.read(map);
    System.out.println("basketno: " + readMap);
    if(readMap != null) {
      int basketno = Integer.parseInt(readMap.get("BASKETNO").toString());
      int basketamount = Integer.parseInt(readMap.get("AMOUNT").toString());
      basketProc.update( (basketamount + amount), memberno, basketno );
      response.put("message", "항목을 추가하였습니다.");
      return response;
    }

    int result = basketProc.create(memberno, color, sizes, amount);
    
    if (result == 1) {
      response.put("success", true);
    } else {
      response.put("message", "장바구니에 추가하는데 실패했습니다.");
    }

    return response;
  }

  @PostMapping(value = "/update")
  @ResponseBody
  public Map<String, Object> update(@RequestBody Map<String, Object> map, HttpSession session) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    Map<String, Object> response = new HashMap<>();
    int memberno = memberVO.getMemberno();
    int basketno = (Integer) map.get("basketno");
    int amount = (Integer) map.get("amount");
    
    System.out.println("amount: " + amount);

    int result = basketProc.update(amount, memberno, basketno);

    if (result == 1) {
      response.put("success", true);
    } else {
      response.put("success", false);
      response.put("message", "수량 변경에 실패했습니다.");
    }

    return response;
  }

  @PostMapping(value = "/delete")
  @ResponseBody
  public Map<String, Object> delete(@RequestBody Map<String, Object> map, HttpSession session) {
    MemberVO memberVO = (MemberVO) session.getAttribute("login");
    Map<String, Object> response = new HashMap<>();
    int memberno = memberVO.getMemberno();
    int basketno = (Integer) map.get("basketno");
    
    int result = basketProc.delete(memberno, basketno);

    // Integer memberno = (Integer) session.getAttribute("memberno");
    // if (memberno == null) {
    // response.put("message", "세션이 만료되었거나 로그인 되어 있지 않습니다.");
    // return response;
    // }
    if (result == 1) {
      response.put("success", true);
    } else {
      response.put("message", "장바구니 제품 삭제에 실패했습니다.");
    }

    return response;
  }

}
