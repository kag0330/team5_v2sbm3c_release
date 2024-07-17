package dev.mvc.team5.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;


import dev.mvc.loginHistory.LoginHistoryProcInter;
import dev.mvc.member.MemberProc;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberRole;
import dev.mvc.member.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  @Autowired
  @Qualifier("dev.mvc.loginHistory.LoginHistoryProc")
  private LoginHistoryProcInter loginHistoryProc;
  
  
  
	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
	    return http.csrf(csrf ->csrf.disable())
	        .authorizeHttpRequests(requests -> requests
	            .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
	            .requestMatchers("/admin/**").hasAnyAuthority(MemberRole.MASTER.name(),MemberRole.ADMIN.name())
//	            .requestMatchers("/admin/**").hasAuthority(MemberRole.MASTER.name())
//	            .requestMatchers("/admin/**").hasAuthority(MemberRole.ADMIN.name())
	            //.requestMatchers("/business/**").hasAuthority(MemberRole.BUSINESS.name())
	            .requestMatchers("/member/**").authenticated()
	            .requestMatchers("/delivery/**").authenticated()
	            .requestMatchers("/basket/**").authenticated()
	            .anyRequest().permitAll()
	            )
	            
	        .formLogin(login -> login
	            .usernameParameter("id")	                    
	            .passwordParameter("pw")
	            .loginPage("/login/signin")
	            .defaultSuccessUrl("/", true)
	            .failureUrl("/login/signin")
	            /*==============로그인 실패시 동작 START============== */
	            .failureHandler((request, response, authException)->{
	              if(authException instanceof DisabledException) {
	                response.sendRedirect("/login/signin?error=disabled");
	              }
	              response.sendRedirect("/login/signin");
	            })/*==============로그인 실패시 동작 END============== */
	            /*==============로그인 성공시 동작 START============== */
	            .successHandler((request, response, authentication) -> {

	                      String id = request.getParameter("id");
	                      String pw = request.getParameter("pw"); // 비밀번호 가져오기
	                      String save = request.getParameter("save");
	                      Cookie[] cookies;
	                      if (save != null) {
	                          // 쿠키 값 설정
	                          cookies = new Cookie[]{new Cookie("ck_id", id), new Cookie("ck_pw", pw), new Cookie("save", "true")};
	                          for (Cookie cookie : cookies) {
	                              cookie.setPath("/");
	                              cookie.setMaxAge(60 * 60 * 24 * 30); // 30일 동안 유효한 쿠키
	                              response.addCookie(cookie);
	                          }
	                      } else {
	                          // 저장하지 않는 경우 쿠키 값 초기화
	                          cookies = new Cookie[]{new Cookie("ck_id", ""), new Cookie("ck_pw", ""), new Cookie("save", "")};
	                          for (Cookie cookie : cookies) {
	                              cookie.setPath("/");
	                              cookie.setMaxAge(0); // 쿠키 삭제
	                              response.addCookie(cookie);
	                          }
	                      }
	                      HttpSession session = request.getSession();
	                      // 사용자 정보 조회
	                      MemberVO memberVO = memberProc.readById(id);
	                      // 세션에 사용자 정보 저장
	                      session.setAttribute("login", memberVO);
	                      
	                      this.loginHistoryProc.create(memberVO.getMemberno(), request);
	                      
	                      // 기본 성공 핸들러 실행
	                      new DefaultRedirectStrategy().sendRedirect(request, response, "/");
 
	                  })/*==============로그인 성공시 동작 END============== */
	            )
	            
	            
	            .logout(logout -> logout
	                .logoutUrl("/login/logout")
	                .logoutSuccessUrl("/")
	                .invalidateHttpSession(true).deleteCookies("JSESSIONID"))
	            
	            
	            .exceptionHandling(handling -> handling
	                .authenticationEntryPoint((AuthenticationEntryPoint) new AuthenticationEntryPoint() {
	                  @Override
	                  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
	                    //인증확인
	                    response.sendRedirect("/authentication");
	                    }}
	                )
	                .accessDeniedHandler((AccessDeniedHandler) new AccessDeniedHandler() {
	                  @Override
	                  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
	                    //인가확인
	                    response.sendRedirect("/authorization");
	                    }
	                  }
	                )
	            ).build();
	    }
}
