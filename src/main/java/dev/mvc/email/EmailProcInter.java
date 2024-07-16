package dev.mvc.email;

public interface EmailProcInter {
  public void send(String sendTO, String sub, String con) throws Exception;
  
  public void send_pw(String sendTO, String originPw) throws Exception;
}
