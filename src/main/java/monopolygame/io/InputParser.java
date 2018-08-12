package monopolygame.io;

public class InputParser {

    private String playerName;
    private String tileName;

    public InputParser(String input) {
        if(input == null || input.length() == 0)
            throw new IllegalArgumentException();
        parse(input);

    }

    public String getPlayerName() {
        return playerName;
    }

    public String getTileName() {
        return tileName;
    }

    private void parse(String input) {
        InputCommand inputCommand = InputCommand.identifyCommand(input);
        String[] split = input.split(inputCommand.getCommand());
        playerName = split[0].trim();
        tileName = split[1].trim();
    }

}
