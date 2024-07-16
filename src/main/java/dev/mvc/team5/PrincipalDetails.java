package dev.mvc.team5;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dev.mvc.member.MemberVO;

public class PrincipalDetails implements UserDetails {

  @Qualifier("dev.mvc.member.MemberProc")
  private MemberVO memberVO;

  public PrincipalDetails(MemberVO memberVO) {
    this.memberVO = memberVO;
  }

  // 권한 관련 작업을 하기 위한 role return
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    Collection<GrantedAuthority> collectors = new ArrayList<GrantedAuthority>();
    collectors.add(() -> {
      return memberVO.getRole().name();
    });

    return collectors;
  }

  // get Password 메서드
  @Override
  public String getPassword() {
    return memberVO.getPw();
  }

  // get Username 메서드 (생성한 User은 loginId 사용)
  @Override
  public String getUsername() {
    return memberVO.getId();
  }

  // 계정이 만료 되었는지 (true: 만료X)
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  // 계정이 잠겼는지 (true: 잠기지 않음)
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  // 비밀번호가 만료되었는지 (true: 만료X)
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  // 계정이 활성화(사용가능)인지 (true: 활성화)
  @Override
  public boolean isEnabled() {
    if (memberVO.getGrade() == 99) {
      return false;
    }
    return true;
  }

}