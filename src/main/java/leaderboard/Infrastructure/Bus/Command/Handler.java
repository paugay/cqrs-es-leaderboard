package leaderboard.Infrastructure.Bus.Command;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Handler {
    public void handle(Command command) throws JsonProcessingException;
}
