package kr.co.adamsoft.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EtcServiceImpl implements EtcService {

	@Override
	public void push(HttpServletRequest request, HttpServletResponse response) {
		//출력 내용을 만들 Output Stream 
		PrintWriter writer = null;
		try {
			//출력 설정
			response.setContentType("text/event-stream");
			response.setCharacterEncoding("utf-8");
			//출력 객체 생성
			writer = response.getWriter();
			
			//기록
			Random r = new Random();
			writer.write("data:" + r.nextInt() + "\n\n");
			Thread.sleep(5000);
			
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		writer.close();

	}

	@Override
	public String proxy(HttpServletRequest request, HttpServletResponse response) {
		String result = null;
		try {
			//파라미터 읽어오기
			String addr = request.getParameter("addr");
			//문자열을 가지고 URL 생성
			URL url = new URL(addr);
			//URL 연결 객체 생성
			HttpURLConnection con = 
				(HttpURLConnection)url.openConnection();
			//옵션 설정
			con.setConnectTimeout(30000);
			con.setUseCaches(false);
			
			//문자열을 추가하면서 작업하기 위한 객체 생성
			StringBuilder sb = new StringBuilder();
			//URLConnection 의 데이터를 문자열로 읽기 위한 스트림 생성
			BufferedReader br = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			
			while(true) {
				//한 줄 읽기
				String line = br.readLine();
				//읽은 데이터가 없으면 중지
				if(line == null) {
					break;
				}
				//읽은 데이터가 있으면 sb에 추가
				sb.append(line + "\n");
			}
			
			br.close();
			con.disconnect();
			
			result = sb.toString();
			
			
			
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return result;
	}
	
	//메일을 보내기 위한 bean 주입
	@Autowired
	private MailSender mailSender;

	@Override
	public void mailsend(HttpServletRequest request, HttpServletResponse response) {
		//보내는 메일 주소 설정 - 자신의 계정을 정확하게 입력
		String setfrom = "ggangpae3@naver.com";
		
		//파라미터 가져오기
		String tomail = request.getParameter("tomail");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		try {
			//메일 객체 생성
			SimpleMailMessage message = 
					new SimpleMailMessage();
			
			message.setFrom(setfrom);
			message.setTo(tomail);
			message.setSubject(title);
			message.setText(content);
			
			//메일 보내기
			mailSender.send(message);
			
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
	}

}
