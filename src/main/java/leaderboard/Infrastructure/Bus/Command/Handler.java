package leaderboard.Infrastructure.Bus.Command;

public interface Handler {
    public void handle(Command command);
}
