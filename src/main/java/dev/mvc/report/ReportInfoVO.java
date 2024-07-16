package dev.mvc.report;

import dev.mvc.member.MemberVO;
import dev.mvc.reportType.ReportTypeVO;
import dev.mvc.review.ReviewVO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReportInfoVO {
  private ReportVO reportVO;
  private ReportTypeVO reportTypeVO;
  private MemberVO memberVO;
  private ReviewVO reviewVO;
}
