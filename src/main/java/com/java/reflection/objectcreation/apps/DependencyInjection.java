package com.java.reflection.objectcreation.apps;

import com.java.reflection.objectcreation.models.game.Game;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class DependencyInjection {

    public static void main(String[] args) throws ClassNotFoundException {
        publicTicTacToeGame();

        privateTicTacToeGame();

    }

    public static void publicTicTacToeGame() {
        //Game game = createObject(TicTacToeGame.class);
        //game.startGame();
    }

    public static void privateTicTacToeGame() throws ClassNotFoundException {
        Game game = createObject(
                (Class<? extends Game>)Class.forName("com.java.reflection.objectcreation.models.game.internal.TicTacToeGame")
        );
        game.startGame();
    }

    public static <T> T createObject(Class<T> clazz) {
        Constructor<T> constructor = getConstructor(clazz);

        ArrayList<Object> parameters = new ArrayList<>();
        for (Class<?> parameterType : constructor.getParameterTypes()) {
            Object parameter = createObject(parameterType);
            parameters.add(parameter);
        }

        try {
            constructor.setAccessible(true);
            return constructor.newInstance(parameters.toArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to create object of type " + clazz.getName(), e);
        }
    }

    public static <T> Constructor<T> getConstructor(Class<T> clazz) {
        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getDeclaredConstructors();

        if (constructors.length == 0) {
            throw new IllegalStateException("No constructor found for " + clazz.getName());
        }

        return constructors[0];
    }
}
