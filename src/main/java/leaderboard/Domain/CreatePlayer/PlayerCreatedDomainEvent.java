package leaderboard.Domain.CreatePlayer;

import leaderboard.Infrastructure.Bus.Event.DomainEvent;

public class PlayerCreatedDomainEvent extends DomainEvent {
    private final static String CLASS_NAME = PlayerCreatedDomainEvent.class.getSimpleName();

    public PlayerCreatedDomainEvent(String aggregateId, String aggregateType, String data) {
        super(CLASS_NAME, aggregateId, aggregateType, data);
    }
}
