package demo.visually_appealing_realtime_update.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {

	private Long id;
	private String name;
	private String message;
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private EvtType type;
}
