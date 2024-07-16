package dev.mvc.team5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;

@Service
public class PrincipalDetailsService implements UserDetailsService {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

//  public PrincipalDetailsService( MemberProcInter memberProc) {
//    this.memberProc = memberProc;
//  }


  @Override
  public UserDetails loadUserByUsername(String id){
    MemberVO memberVO = memberProc.readById(id);
    if(memberVO != null) {
      return new PrincipalDetails(memberVO);
    }
    return null;
  }
}