package dev.mvc.shoesInquiry;

import dev.mvc.member.MemberVO;
import dev.mvc.option.OptionVO;
import dev.mvc.shoes.ShoesVO;
import lombok.Getter;
import lombok.Setter;

/** inquiry 테이블에 보여줄 데이터 */
/** 목록(list) -> 번호, 문의 제목, 날짜, 답변 여부, 문의작성자 */ 
/** 상세(read) ->문의 제목, 작성자, 신발이름, 사이즈&색상, 내용, 답변하기 답변 내용  */
@Setter @Getter
public class ShoesInquiryInfoVO {
  private ShoesInquiryVO shoesInquiryVO;
  private ShoesVO shoesVO;
  private OptionVO optionVO;
  private MemberVO memberVO;
}
