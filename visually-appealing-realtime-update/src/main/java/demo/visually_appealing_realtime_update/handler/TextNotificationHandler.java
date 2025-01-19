package demo.visually_appealing_realtime_update.handler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component


@Slf4j
public class TextNotificationHandler extends TextWebSocketHandler {
	
	private final ObjectMapper  mapper=new ObjectMapper();
	
	
	private Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.debug("afterConnectionEstablished :: session established remote host: {}",session.getRemoteAddress());
		sessions.add(session);
		log.debug("afterConnectionEstablished :: connection from: {} is added. Current Open session count : {}",session.getRemoteAddress(),sessions.size());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		for (WebSocketSession webSocketSession : sessions) {
			if (webSocketSession.isOpen()) {
				webSocketSession.sendMessage(message);
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.debug("afterConnectionEstablished :: session closed remote host: {}",session.getRemoteAddress());
		sessions.remove(session);
		log.debug("afterConnectionEstablished :: connection from: {} is removed. Current Open session count : {}",session.getRemoteAddress(),sessions.size());
	}
}
