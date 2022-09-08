package org.minerslore;

import org.minerslore.Actors.Actor;
import org.minerslore.Items.*;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Our Dictionary
public class ActionsDict {
    static Object minerObj; // convert string classname to class

    static {
        try {
            minerObj = Class.forName("org.minerslore.ActionsDict").getDeclaredConstructor().newInstance();
        } catch (InstantiationException | ClassNotFoundException | NoSuchMethodException | RuntimeException |
                 InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    static Class<?>[] paramUserCommandTypes = {Actor.class};
    public static Map<Character, String> userCommands = new HashMap<>(
            Map.of('N', "goNorth",
                    'S', "goSouth",
                    'E', "goEast",
                    'W', "goWest",
                    'D', "actionDig"
            )
    );

    public static void goNorth(Actor actor) throws IOException {
        actor.moveActor(actor.getN());
    }

    public static void goSouth(Actor actor) throws IOException {
        actor.moveActor(actor.getS());
    }

    public static void goEast(Actor actor) throws IOException {
        actor.moveActor(actor.getE());
    }

    public static void goWest(Actor actor) throws IOException {
        actor.moveActor(actor.getW());
    }

    public static void actionDig(Actor actor) {
        Item block = (Item) actor.getOn_Block();
        CavePath tile = (CavePath) block;
        if (tile.isPath() == true) {
            tile.interact(actor);
        }
    }

    public static void actorActions(Actor actor, char ch) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        if (userCommands.containsKey(ch)) {
            System.out.println(ch);
            minerObj.getClass().getMethod(userCommands.get(ch), paramUserCommandTypes).invoke(userCommands.get(ch), actor);
        } else {
            System.out.println("Miner is confused.");
        }
    }

}
