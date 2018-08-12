package monopolygame;

import monopolygame.io.InputParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;


public class Board {

    private Map<String, Player> playerMap = new
            LinkedHashMap<>();
    private Map<String, Tile> tilemap = new
            LinkedHashMap<>();
    private List<Property> propertyList = new ArrayList<>();

    public Board(String jsonStr) {
        initialize(jsonStr);
    }

    private void initialize(String jsonStr) {
        JSONObject jsonObject = new JSONObject(jsonStr);
        JSONArray playersJsonArr = jsonObject.getJSONArray("players");
        if(playersJsonArr == null) {
            throw new IllegalArgumentException("Invalid json string");
        }
        initializePlayer(playersJsonArr);
        JSONArray propertiesJsonArr = jsonObject.getJSONArray("properties");
        if(propertiesJsonArr == null) {
            throw new IllegalArgumentException("Invalid json string");
        }
        initializeProperties(propertiesJsonArr);
    }

    public void processCommand(String input) {
        InputParser inputParser = new InputParser(input);
        String playerName = inputParser.getPlayerName();
        String tileName = inputParser.getTileName();
        Player player = playerMap.get(playerName);
        Tile tile = tilemap.get(tileName);
        tile.playerLanded(player);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(playerMap.values());
    }

    public List<Property> getPropertis() {
        return propertyList;
    }

    private void initializeProperties(JSONArray propertiesJsonArr) {
        for (int i = 0; i < propertiesJsonArr.length(); i++) {
            JSONObject prop = propertiesJsonArr.getJSONObject(i);
            String name = prop.getString("name");
            int cost = prop.getInt("cost");
            String color = prop.getString("color");
            JSONArray rentJsonArr = prop.getJSONArray("rentPerLevel");
            int[] rentPerLevel = new int[rentJsonArr.length()];
            for (int j = 0; j < rentJsonArr.length(); j++) {
                rentPerLevel[j] = rentJsonArr.getInt(j);
            }

            Property property = new Property(name, cost, Color.valueOf(color)
                    , rentPerLevel);
            propertyList.add(property);

        }

        SpecialTile specialTile = new SpecialTile(new ArrayList<Player>(playerMap.values()));
        GO goTile = new GO();


        tilemap.put(goTile.getName(), goTile);
        tilemap.put(specialTile.getName(), specialTile);

        for (Property property : propertyList) {
            tilemap.put(property.getName(), property);
        }
    }

    private  void initializePlayer(JSONArray playersJsonArr) {
        Iterator<Object> iterator = playersJsonArr.iterator();
        while (iterator.hasNext()) {
            String playerName = (String) iterator.next();
            Player player = new Player(playerName);
            playerMap.put(player.getName(), player);
        }
    }


}