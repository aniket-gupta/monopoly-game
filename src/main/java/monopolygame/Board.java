package monopolygame;

import monopolygame.input.InputParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Hello world!
 */
public class Board {
    public static void main(String[] args) {
        String[] inputs = {
                "Player A lands on Bombay",
                "Player B lands on Delhi",
                "Player C lands on Chennai",
                "Player A lands on Special",
                "Player B lands on Bombay",
                "Player C lands on Ooty",
                "Player A lands on Ahmedabad",
                "Player B lands on Darjeeling",
                "Player C lands on Special",
                "Player A lands on Delhi",
                "Player B crosses GO",
                "Player C lands on Bombay"
        };

        LinkedHashMap<String, Player> map = new LinkedHashMap<>();

        map.put("Player A", new Player("Player A"));
        map.put("Player B", new Player("Player B"));
        map.put("Player C", new Player("Player C"));

        SpecialTile specialTile = new SpecialTile(new ArrayList<Player>(map
                .values()));
        GO goTile = new GO();

        LinkedHashMap<String, Tile> tilemap = new
                LinkedHashMap<>();

        tilemap.put(goTile.getName(), goTile);
        tilemap.put(specialTile.getName(), specialTile);

        List<Property> propertyList = Arrays.asList(
                new Property("Cochin", 120, Color.Green,
                        new int[]{100, 160, 260, 440, 860}),
                new Property("Ooty", 400, Color.Green,
                        new int[]{300, 400, 560, 810, 1600}),
                new Property("Bombay", 500, Color.Red,
                        new int[]{400, 520, 680, 900, 1800}),
                new Property("Ahmedabad", 300, Color.Red,
                        new int[]{200, 350, 480, 800, 1200}),
                new Property("Chennai", 700, Color.Blue,
                        new int[]{600, 900, 1250, 1500, 1900}),
                new Property("Bangalore", 450, Color.Blue,
                        new int[]{300, 400, 560, 810, 1600}),
                new Property("Delhi", 500, Color.Yellow,
                        new int[]{400, 520, 680, 900, 1800}),
                new Property("Darjeeling", 600, Color.Yellow,
                        new int[]{400, 700, 1000, 1150, 1400})
        );

        for (Property property : propertyList) {
            tilemap.put(property.getName(), property);
        }


        for (String input : inputs) {
            InputParser inputParser = new InputParser(input);
            String playerName = inputParser.getPlayerName();
            String tileName = inputParser.getTileName();
            Player player = map.get(playerName);

            Tile tile = tilemap.get(tileName);
            tile.update(player);
        }

        System.out.println("Players:");
        for (Player player : map.values()) {
            System.out.println(player);
        }

        System.out.println();
        System.out.println("Properties");
        for (Property property : propertyList) {
            System.out.println(property);
        }

    }
}
