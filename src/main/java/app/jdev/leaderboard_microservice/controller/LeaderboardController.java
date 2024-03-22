package app.jdev.leaderboard_microservice.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    private final RedisTemplate<String, String> redisTemplate;

    public LeaderboardController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping
    public Set<ZSetOperations.TypedTuple<String>> getLeaderboard() {
        String leaderboardKey = "leaderboard";
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();

        return zSetOperations.reverseRangeWithScores(leaderboardKey, 0 ,9);
    }

}
