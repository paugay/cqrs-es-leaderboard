package leaderboard.Types;

import leaderboard.Infrastructure.Bus.Event.DomainEvent;

import java.util.List;

public interface EventStore {
    List<DomainEvent> load(String id);

    void append(DomainEvent domainEvent);
}
