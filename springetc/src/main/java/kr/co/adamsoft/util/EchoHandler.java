package kr.co.adamsoft.util;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//기본 패키지 안에 작성되면 bean을 자동으로 생성해 줌
@Component
public class EchoHandler extends TextWebSocketHandler {

	//클라이언트가 연결되면 호출되는 메서드
	@Override
	//session 에 접속한 클라이언트 정보가 전달
	public void afterConnectionEstablished(
			WebSocketSession session) {
		try {
			System.out.println(session.getId() + "연결");
		}catch(Exception e) {
			System.out.println("1:" + e.getLocalizedMessage());
		}
	}
	
	//클라이언트가 메시지를 전송한 경우 호출되는 메서드
	@Override
	public void handleTextMessage(
			WebSocketSession session, TextMessage message) {
		try {
			//메시지 확인
			System.out.println(session.getId() + " 로 부터 "
					 + message.getPayload() + " 받음");
			//받은 메시지를 그대로 다시 전송
			session.sendMessage(new TextMessage("echo:" + 
			message.getPayload()));
			
		}catch(Exception e) {
			System.out.println("2:" + e.getLocalizedMessage());
		}
	}
	
	//클라이언트의 접속이 끊어지면 호출되는 메서드
	@Override
	public void afterConnectionClosed(
			WebSocketSession session, CloseStatus status) {
		try {
			//메시지 확인
			System.out.println(session.getId() + " 해제 ");
			
		}catch(Exception e) {
			System.out.println("2:" + e.getLocalizedMessage());
		}
	}
}






