package monopolygame.input;

public enum InputCommand {
    LANDS_ON("lands on"),
    CROSS("crosses");

    final String command;

    InputCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static InputCommand identifyCommand(String input) {
        if(input != null) {
            for (InputCommand command : InputCommand.values()) {
                if (input.contains(command.command)) {
                    return command;
                }
            }
        }
        return null;
    }
}
