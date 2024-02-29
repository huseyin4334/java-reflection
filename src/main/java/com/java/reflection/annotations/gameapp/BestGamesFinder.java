package com.java.reflection.annotations.gameapp;

import com.java.reflection.annotations.models.databases.Database;
import java.util.*;

import static com.java.reflection.annotations.models.annotations.GameAnnotations.*;

public class BestGamesFinder {
    private Database database = new Database();


    @Operation("all-games")
    public Set<String> getAllGames() {
        return database.readAllGames();
    }

    @Operation("game-price")
    public Map<String, Float> getGameToPrice(@DependsOn("all-games") Set<String> allGames) {
        return database.readGameToPrice(allGames);
    }

    @Operation("game-rating")
    public Map<String, Float> getGameToRating(@DependsOn("all-games") Set<String> allGames) {
        return database.readGameToRatings(allGames);
    }

    @Operation("score-games")
    public SortedMap<Double, String> scoreGames(@DependsOn("game-price") Map<String, Float> gameToPrice,
                                                @DependsOn("game-rating") Map<String, Float> gameToRating) {
        SortedMap<Double, String> gameToScore = new TreeMap<>(Collections.reverseOrder());
        for (String gameName : gameToPrice.keySet()) {
            double score = (double) gameToRating.get(gameName) / gameToPrice.get(gameName);
            gameToScore.put(score, gameName);
        }

        return gameToScore;
    }

    @FinalResult
    public List<String> getTopGames(@DependsOn("score-games") SortedMap<Double, String> gameToScore) {
        return new ArrayList<>(gameToScore.values());
    }
}

