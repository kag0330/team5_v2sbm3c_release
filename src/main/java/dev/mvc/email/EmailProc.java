package dev.mvc.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;



@Service
public class EmailProc implements EmailProcInter {
  @Value("${spring.mail.username}")
  private String sender;
  
  @Autowired
  private JavaMailSender jms;
  
  public void send(String sendTO, String sub, String con) throws Exception{
    MimeMessage mm = jms.createMimeMessage();
    mm.setFrom(this.sender);
    
    mm.addRecipient(RecipientType.TO, new InternetAddress(sendTO));
    mm.setSubject(sub);
    mm.setText(con);
    jms.send(mm);
  }
  
  
    public void send_pw(String sendTO, String originPw) throws Exception{
    MimeMessage mm = jms.createMimeMessage();
    mm.setFrom(this.sender);
    
    mm.addRecipient(RecipientType.TO, new InternetAddress(sendTO));
    mm.setSubject("[발걸음] 변경된 비밀번호를 확인하세요.");
    mm.setText("변경된 비밀번호: " + originPw);
    jms.send(mm);
  }
}
