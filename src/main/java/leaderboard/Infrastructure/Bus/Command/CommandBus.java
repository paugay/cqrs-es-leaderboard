package leaderboard.Infrastructure.Bus.Command;

public interface CommandBus {
    void register(Class<? extends Command> commandType, Handler handler);

    void dispatch(Command command);
}
