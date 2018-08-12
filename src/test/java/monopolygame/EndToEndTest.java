package monopolygame;

import monopolygame.io.InputParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class EndToEndTest {

    private Map<String, Player> playerMap;
    private Map<String, Tile> tilemap;
    private List<Property> propertyList;

    @Before
    public void setup() {
        playerMap = new HashMap<>();
        tilemap = new HashMap<>();
        playerMap.put("Player A", new Player("Player A"));
        playerMap.put("Player B", new Player("Player B"));
        playerMap.put("Player C", new Player("Player C"));

        SpecialTile specialTile = new SpecialTile(new ArrayList<Player>(playerMap
                .values()));
        GO goTile = new GO();
        tilemap.put(goTile.getName(), goTile);
        tilemap.put(specialTile.getName(), specialTile);

        propertyList = Arrays.asList(
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
    }

    @Test
    public void sampleInputTest() {
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

        processInput(inputs);

        // check player balance
        Assert.assertEquals(1840, playerMap.get("Player A").getMoney());
        Assert.assertEquals(980, playerMap.get("Player B").getMoney());
        Assert.assertEquals(380, playerMap.get("Player C").getMoney());

        // check properties
        checkProperty(null, "Cochin", 0, Color.Green);

        checkProperty("Player C", "Ooty", 1, Color.Green);

        checkProperty("Player A", "Bombay", 3, Color.Red);

        checkProperty("Player A", "Ahmedabad", 1, Color.Red);

        checkProperty("Player C", "Chennai", 2, Color.Blue);

        checkProperty(null, "Bangalore", 0, Color.Blue);

        checkProperty("Player B", "Delhi", 2, Color.Yellow);

        checkProperty("Player B", "Darjeeling", 1, Color.Yellow);
    }

    private void checkProperty(String s, String darjeeling, int i, Color
            color) {
        Player owner = ((Property) tilemap.get(darjeeling)).getOwner();
        Assert.assertEquals(s, owner == null ? null : owner.getName());
        Assert.assertEquals(i, ((Property) tilemap.get(darjeeling))
                .currentRentLevel() + 1);
        Assert.assertEquals(color, tilemap.get(darjeeling).getColor());
    }

    private void processInput(String[] inputs) {
        for (String input : inputs) {
            InputParser inputParser = new InputParser(input);
            String playerName = inputParser.getPlayerName();
            String tileName = inputParser.getTileName();
            Player player = playerMap.get(playerName);

            Tile tile = tilemap.get(tileName);
            tile.playerLanded(player);
        }
    }
}
