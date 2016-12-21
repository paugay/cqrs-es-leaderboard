package leaderboard.infrastructure.bus.event;

import java.util.Date;
import java.util.UUID;

public abstract class DomainEvent {
    private UUID eventId;
    private String eventType;
    private String aggregateId;
    private String aggregateType;
    private String data;
    private Date occuredOn;

    public DomainEvent(String eventType, String aggregateId, String aggregateType, String data) {
        this.eventId = UUID.randomUUID();
        this.eventType = eventType;

        guardAggregateId();

        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType;
        this.data = data;
        this.occuredOn = new Date();
    }

    public UUID getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public String getData() {
        return data;
    }

    public Date getOccuredOn() {
        return occuredOn;
    }

    private void guardAggregateId() {
        // todo

        // why aggregate id is not an Identifier?
    }

    @Override
    public String toString() {
        return "DomainEvent{" +
                "eventId=" + eventId +
                ", eventType='" + eventType + '\'' +
                ", aggregateId='" + aggregateId + '\'' +
                ", aggregateType='" + aggregateType + '\'' +
                ", data='" + data + '\'' +
                ", occuredOn=" + occuredOn +
                '}';
    }


}
