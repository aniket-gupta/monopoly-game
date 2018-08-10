package monopolygame;

import monopolygame.input.InputParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
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
        GoTile goTile = new GoTile();

        LinkedHashMap<String, Property> propertyMap = new LinkedHashMap<>();

        propertyMap.put("Cochin", new Property("Cochin", 120, Color.Green,
                new int[] {100,160,260,440,860}));
        propertyMap.put("Ooty", new Property("Ooty", 400, Color.Green,
                new int[] {300,400,560,810,1600}));
        propertyMap.put("Bombay", new Property("Bombay", 500, Color.Red,
                new int[] {400,520,680,900,1800}));

        propertyMap.put("Ahmedabad", new Property("Ahmedabad", 300, Color.Red,
                new int[] {200,350,480,800,1200}));
        propertyMap.put("Chennai", new Property("Chennai", 700, Color.Blue,
                new int[] {600,900,1250,1500,1900}));
        propertyMap.put("Bangalore", new Property("Bangalore", 450, Color.Blue,
                new int[] {300,400,560,810,1600}));
        propertyMap.put("Delhi", new Property("Delhi", 500, Color.Yellow,
                new int[] {400,520,680,900,1800}));
        propertyMap.put("Darjeeling", new Property("Darjeeling", 600, Color.Yellow,
                new int[] {400,700,1000,1150,1400}));


        for(String input : inputs) {
            InputParser inputParser = new InputParser(input);
            String playerName = inputParser.getPlayerName();
            String tileName = inputParser.getTileName();
            Player player = map.get(playerName);
            if(tileName.equalsIgnoreCase("GO")) {
                goTile.performAction(player);
            } else if(tileName.equalsIgnoreCase("Special")) {
                specialTile.performAction(player);
            } else {
                Property property = propertyMap.get(tileName);
                player.landOn(property);
            }
        }

        System.out.println("Players:");
        for(Player player : map.values()) {
            System.out.println(player);
        }

        System.out.println();
        System.out.println("Properties");
        for (Property property : propertyMap.values()) {
            System.out.println(property);
        }


    }
}
