package demo.visually_appealing_realtime_update.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import demo.visually_appealing_realtime_update.handler.TextNotificationHandler;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

	private static final String PATH_SCORE = "score";

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new TextNotificationHandler(), PATH_SCORE).setAllowedOrigins("*");
	}

}
