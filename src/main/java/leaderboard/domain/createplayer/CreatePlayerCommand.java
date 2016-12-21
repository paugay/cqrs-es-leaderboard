package leaderboard.domain.createplayer;

import leaderboard.infrastructure.bus.command.Command;

final public class CreatePlayerCommand implements Command {
    private final String username;
    private final String name;
    private final String email;

    public CreatePlayerCommand(String username, String name, String email) {
        this.username = username;
        this.name = name;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "CreatePlayerCommand{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
