package monopolygame;

public class GO extends Tile {

    public GO() {
        super("GO");
    }

    @Override
    public void update(Player player) {
        player.collect(200);
    }
}
