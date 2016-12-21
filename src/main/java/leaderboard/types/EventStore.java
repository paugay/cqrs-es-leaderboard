package leaderboard.types;

import leaderboard.infrastructure.bus.event.DomainEvent;

import java.util.List;

public interface EventStore {
    List<DomainEvent> load(String id);

    void append(DomainEvent domainEvent);
}
