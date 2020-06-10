package ch.hesge.cours634.exam;

import javax.persistence.*;
import java.time.LocalDateTime;

public class AccessEventEntity {
	private int eventId;
	@Enumerated(EnumType.STRING)
	private AccessStatus accessStatus;
	private LocalDateTime accessTime;
	@Enumerated(EnumType.STRING)
	private AccessDirection direction;

}
