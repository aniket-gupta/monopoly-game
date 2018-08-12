package monopolygame;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class App {

    public static void main(String[] args) {
        if(args == null || args.length == 0) {
            System.err.println("Usage: java -jar <your jar file> <input file>");
            return;
        }

        String str = readFile(args[0]);
        final JSONObject jsonObject = new JSONObject(str);
        final JSONObject initJsonObject = jsonObject.getJSONObject("init");
        Board board = new Board(initJsonObject.toString());
        JSONArray inputJsonArr = jsonObject.getJSONArray("input");

        for(int i = 0; i < inputJsonArr.length(); i++) {
            board.processCommand(inputJsonArr.getString(i));
        }
        List<Player> players = board.getPlayers();

        System.out.println("PLayers:");
        for (Player player: players) {
            System.out.println(player);
        }
        List<Property> properties = board.getPropertis();

        System.out.println("\nProperties:");

        for (Property property : properties) {
            System.out.println(property);
        }

    }

    private static String readFile(String filename) {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
