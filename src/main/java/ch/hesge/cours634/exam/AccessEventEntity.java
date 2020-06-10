package ch.hesge.cours634.exam;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AccessEventEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eventId;
	@Enumerated(EnumType.STRING)
	private AccessStatus accessStatus;
	private LocalDateTime accessTime;
	@Enumerated(EnumType.STRING)
	private AccessDirection direction;

}
