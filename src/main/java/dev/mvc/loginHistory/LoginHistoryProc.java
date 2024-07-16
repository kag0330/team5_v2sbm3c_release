package dev.mvc.loginHistory;

import java.net.UnknownHostException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.mvc.member.MemberDAOInter;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpServletRequest;

@Service
@Component("dev.mvc.loginHistory.LoginHistoryProc")
public class LoginHistoryProc implements LoginHistoryProcInter {
  @Autowired
  private LoginHistoryDAOInter loginHistoryDAO;
  
  @Autowired
  private MemberDAOInter memberDAO;
  

  @Override
  public int create(int memberno, HttpServletRequest request) {
    LoginHistoryVO lhVO = new LoginHistoryVO();
    try {
      lhVO.setIp(Tool.getClientIp(request));
      lhVO.setMemberno(memberno);
    } catch (UnknownHostException e) {      
      e.printStackTrace();
    }
    
    System.out.println(lhVO.getIp());
    return this.loginHistoryDAO.create(lhVO);
  }

  @Override
  public int countByMemberno(int memberno) {
    return this.loginHistoryDAO.countByMemberno(memberno);
  }

  @Override
  public ArrayList<LoginHistoryVO> readByMembernoRdateDesc(int memberno) {
    return this.loginHistoryDAO.readByMembernoRdateDesc(memberno);
  }
  
  @Override
  @Transactional
  public int delete(int memberno) {
    return this.loginHistoryDAO.delete(memberno);
  }

  @Override
  @Transactional
  public int deleteOld(int memberno) {
    return this.loginHistoryDAO.deleteOld(memberno);
  }

}
