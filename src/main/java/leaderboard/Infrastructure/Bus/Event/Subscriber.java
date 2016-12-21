package leaderboard.Infrastructure.Bus.Event;

public interface Subscriber {
    public void subscribe(DomainEvent domainEvent);
}
