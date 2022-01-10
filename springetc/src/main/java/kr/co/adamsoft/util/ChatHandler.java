package kr.co.adamsoft.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatHandler extends TextWebSocketHandler {
	//접속자 정보를 저장할 Map
	//세션 ID를 이용해서 구분
	private static Map<String, WebSocketSession> users = 
			new HashMap<>();
	
	//클라이언트가 연결되면 호출되는 메서드
	@Override
	//session 에 접속한 클라이언트 정보가 전달
	public void afterConnectionEstablished(
			WebSocketSession session) {
		System.out.println(session.getId() + "연결");
		//접속한 유저 저장
		users.put(session.getId(), session);
	}
	
	//클라이언트가 메시지를 전송한 경우 호출되는 메서드
	@Override
	public void handleTextMessage(
			WebSocketSession session, TextMessage message){
		//모든 메시지를 유저들에게 전송
		//메시지 보낼 때 유저 이름 과 메시지를 : 을 이용해서 구분
		String msg = message.getPayload();
		System.out.println(msg);
		for(WebSocketSession s : users.values()) {
			try {
				s.sendMessage(new TextMessage(msg));
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}
	
	//클라이언트의 접속이 끊어지면 호출되는 메서드
	@Override
	public void afterConnectionClosed(
			WebSocketSession session, CloseStatus status) {
		System.out.println(session.getId() + "해제");
		//접속한 유저 저장
		users.remove(session.getId());
	}
}





