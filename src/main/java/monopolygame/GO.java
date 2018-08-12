package monopolygame;

public class GO extends Tile {

    public GO() {
        super("GO", Color.None);
    }

    @Override
    protected void performAction(Player player) {
        player.collect(200);
    }
}
