package leaderboard.infrastructure.bus.command;

public interface CommandBus {
    void register(Class<? extends Command> commandType, Handler handler);

    void dispatch(Command command);
}
