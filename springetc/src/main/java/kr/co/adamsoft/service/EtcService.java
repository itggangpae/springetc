package kr.co.adamsoft.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface EtcService {
	//push 요청을 처리할 메서드를 선언
	public void push(
			HttpServletRequest request, 
			HttpServletResponse response);
	
	//proxy 요청을 처리할 메서드를 선언
	//다운로드 받은 문자열을 리턴 - Controller에서 데이터를 저장
	public String proxy(
				HttpServletRequest request, 
				HttpServletResponse response);
	
	//메일보내기 요청을 처리할 메서드를 선언
	public void mailsend(
					HttpServletRequest request, 
					HttpServletResponse response);
	
}
