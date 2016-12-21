package leaderboard.Domain;

public interface PlayerRepository {
    void add(Player player);

    Player find(String username);
}
