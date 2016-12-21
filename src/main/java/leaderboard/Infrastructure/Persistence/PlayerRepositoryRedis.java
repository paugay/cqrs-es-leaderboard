package leaderboard.Infrastructure.Persistence;

import com.google.common.collect.ImmutableMap;
import leaderboard.Domain.Player;
import leaderboard.Domain.PlayerRepository;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class PlayerRepositoryRedis implements PlayerRepository {
    private final static String REDIS_HASH_PREFIX = "player:";

    private Jedis jedis;

    public PlayerRepositoryRedis(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void add(Player player) {
        jedis.hmset(
                REDIS_HASH_PREFIX + player.getUsername(),
                ImmutableMap.of(
                        "username", player.getUsername(),
                        "name", player.getName(),
                        "email", player.getEmail()));
    }

    @Override
    public Player find(String username) {
        Map<String, String> data = jedis.hgetAll(REDIS_HASH_PREFIX + username);

        return new Player(
                data.get("username"),
                data.get("name"),
                data.get("email"));
    }
}
