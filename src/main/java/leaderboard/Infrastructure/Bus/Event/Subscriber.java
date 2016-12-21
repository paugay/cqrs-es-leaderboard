package leaderboard.Infrastructure.Bus.Event;

import java.io.IOException;

public interface Subscriber {
    public void subscribe(DomainEvent domainEvent) throws IOException;
}
