package leaderboard.Domain;

import leaderboard.Domain.CreatePlayer.PlayerCreatedDomainEvent;
import leaderboard.Types.Aggregate.AggregateRoot;

import java.io.Serializable;

public class Player extends AggregateRoot implements Serializable {
    final private PlayerId id;
    final private String username;
    final private String name;
    final private String email;

    public Player(PlayerId id, String username, String name, String email) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
    }

    public static Player create(PlayerId id, String username, String name, String email) {
        Player player = new Player(id, username, name, email);

        // TODO: data, needs to be structured, I guess
        player.raise(new PlayerCreatedDomainEvent(id.toString(), Player.class.getSimpleName(), player.toString()));

        return player;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
