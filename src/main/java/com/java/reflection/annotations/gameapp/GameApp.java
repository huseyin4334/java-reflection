package com.java.reflection.annotations.gameapp;

import com.java.reflection.annotations.models.databases.SqlQueryBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class GameApp {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        BestGamesFinder bestGamesFinder = new BestGamesFinder();

        List<String> bestGames = GameOperations.execute(bestGamesFinder);

        System.out.println("Best games: " + bestGames);


        // Database Example
        SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder(
                List.of("1", "2", "3"),
                10,
                "Movies",
                List.of("Id", "Name")
        );

        String selectQuery = GameOperations.execute(sqlQueryBuilder);

        System.out.println("Select Query: " + selectQuery);

    }
}
