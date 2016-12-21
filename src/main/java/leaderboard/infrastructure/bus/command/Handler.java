package leaderboard.infrastructure.bus.command;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Handler {
    public void handle(Command command) throws JsonProcessingException;
}
