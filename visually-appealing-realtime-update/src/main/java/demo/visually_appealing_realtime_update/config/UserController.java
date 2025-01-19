package demo.visually_appealing_realtime_update.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/viewer")
	public String tvViewer() {
		return "Consumer";
	}
	
	
	@GetMapping("/operator")
	public String mediaControlRoomOperator() {
		return "Publisher";
	}
}
