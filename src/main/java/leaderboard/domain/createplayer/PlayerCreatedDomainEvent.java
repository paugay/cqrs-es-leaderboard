package leaderboard.domain.createplayer;

import leaderboard.infrastructure.bus.event.DomainEvent;

public class PlayerCreatedDomainEvent extends DomainEvent {
    private final static String CLASS_NAME = PlayerCreatedDomainEvent.class.getSimpleName();

    public PlayerCreatedDomainEvent(String aggregateId, String aggregateType, String data) {
        super(CLASS_NAME, aggregateId, aggregateType, data);
    }
}
