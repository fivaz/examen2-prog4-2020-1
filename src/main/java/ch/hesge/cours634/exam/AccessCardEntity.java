package ch.hesge.cours634.exam;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class AccessCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    private LocalDate startValidity;

    private LocalDate endValidity;

    private boolean isEnabled;

    @ManyToOne
    private PersonEntity owner;

    public AccessCardEntity() {
    }

    public AccessCardEntity(LocalDate startValidity, LocalDate endValidity, boolean isEnabled) {
        this.startValidity = startValidity;
        this.endValidity = endValidity;
        this.isEnabled = isEnabled;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public LocalDate getStartValidity() {
        return startValidity;
    }

    public void setStartValidity(LocalDate startValidity) {
        this.startValidity = startValidity;
    }

    public LocalDate getEndValidity() {
        return endValidity;
    }

    public void setEndValidity(LocalDate endValidity) {
        this.endValidity = endValidity;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public PersonEntity getOwner() {
        return owner;
    }

    public void setOwner(PersonEntity owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccessCardEntity)) return false;
        AccessCardEntity that = (AccessCardEntity) o;
        return isEnabled == that.isEnabled &&
                Objects.equals(cardId, that.cardId) &&
                Objects.equals(startValidity, that.startValidity) &&
                Objects.equals(endValidity, that.endValidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, startValidity, endValidity, isEnabled);
    }

    @Override
    public String toString() {
        return "AccessCardEntity{" +
                "cardId=" + cardId +
                ", startValidity=" + startValidity +
                ", endValidity=" + endValidity +
                ", isEnabled=" + isEnabled +
                ", owner=" + owner +
                '}';
    }
}
