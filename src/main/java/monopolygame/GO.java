package monopolygame;

public class GO extends Tile {

    public GO() {
        super("GO");
    }

    @Override
    public void action(Player player) {
        player.collect(200);
    }
}
