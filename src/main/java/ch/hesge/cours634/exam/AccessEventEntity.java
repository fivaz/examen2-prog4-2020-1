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
	@OneToOne
	private AccessCardEntity card;

	public AccessEventEntity() {
	}

	public AccessEventEntity(AccessStatus accessStatus, LocalDateTime accessTime, AccessDirection direction, AccessCardEntity card) {
		this.accessStatus = accessStatus;
		this.accessTime = accessTime;
		this.direction = direction;
		this.card = card;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public AccessStatus getAccessStatus() {
		return accessStatus;
	}

	public void setAccessStatus(AccessStatus accessStatus) {
		this.accessStatus = accessStatus;
	}

	public LocalDateTime getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(LocalDateTime accessTime) {
		this.accessTime = accessTime;
	}

	public AccessDirection getDirection() {
		return direction;
	}

	public void setDirection(AccessDirection direction) {
		this.direction = direction;
	}

	public AccessCardEntity getCard() {
		return card;
	}

	public void setCard(AccessCardEntity card) {
		this.card = card;
	}

	@Override
	public String toString() {
		return "AccessEventEntity{" +
				"eventId=" + eventId +
				", accessStatus=" + accessStatus +
				", accessTime=" + accessTime +
				", direction=" + direction +
				", card=" + card +
				'}';
	}
}
