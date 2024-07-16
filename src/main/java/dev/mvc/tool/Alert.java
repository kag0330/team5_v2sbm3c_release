package dev.mvc.tool;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Map;

@Getter
public class Alert {

  /** 사용자에게 전달할 메시지 */
	private String message; 
	
	/** 리다이렉트할 URI */
	private String redirectUri;
	
	/** HTTP 요청 메서드 GET, POST */
	private RequestMethod method;
	
	/** 전달할 데이터 Map */
	private Map<String, Object> data;

	/** Thymeleaf 를 활용한 alert 창 띄우기
	 * 
	 * @param message     사용자에게 전달할 메시지
	 * @param redirectUri 리다이렉트할 URI
	 * @param method      HTTP 요청 메서드 GET, POST
	 * @param data        전달할 데이터
	 * */
  public Alert(String message, String redirectUri, RequestMethod method, Map<String, Object> data) {
    this.message = message;
    this.redirectUri = redirectUri;
    this.method = method;
    this.data = data;
  }

	
}
