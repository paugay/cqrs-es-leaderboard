package leaderboard.infrastructure.persistence;

import leaderboard.domain.Leaderboard;
import leaderboard.domain.Player;
import leaderboard.domain.PlayerRepository;
import leaderboard.domain.Ranking;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class LeaderboardRedis implements Leaderboard {
    private final static String REDIS_SORTED_SET_KEY = "leaderboard";

    private Jedis jedis;
    private PlayerRepository playerRepository;

    public LeaderboardRedis(
            Jedis jedis,
            PlayerRepository playerRepository) {
        this.jedis = jedis;
        this.playerRepository = playerRepository;
    }

    @Override
    public void updatePlayer(String username, int score) {
        jedis.zadd(REDIS_SORTED_SET_KEY, score, username);
    }

    @Override
    public int getPlayerScore(String username) {
        return jedis.zscore(REDIS_SORTED_SET_KEY, username).intValue();
    }

    @Override
    public Ranking getRanking() {
        LinkedHashSet<Tuple> result = (LinkedHashSet) jedis.zrevrangeWithScores(REDIS_SORTED_SET_KEY, 0, -1);

        Ranking ranking = new Ranking();

        LinkedHashMap<Integer, String> scoreToUsernameMap = new LinkedHashMap<>();
        for (Tuple tuple : result) {
            String username = tuple.getElement();

            Player temporaryPlayer = playerRepository.find(username);

            ranking.put((int) tuple.getScore(), temporaryPlayer);
        }

        return ranking;
    }
}
