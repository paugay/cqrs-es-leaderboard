package leaderboard.domain.addgame;

import leaderboard.infrastructure.bus.event.DomainEvent;

public class GameAddedDomainEvent extends DomainEvent {
    private final static String CLASS_NAME = GameAddedDomainEvent.class.getSimpleName();

    public GameAddedDomainEvent(String aggregateId, String aggregateType, String data) {
        super(CLASS_NAME, aggregateId, aggregateType, data);
    }
}
