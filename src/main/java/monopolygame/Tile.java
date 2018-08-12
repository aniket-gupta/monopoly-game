package monopolygame;

public abstract class Tile {

    private String name;
    private Color color;

    public Tile(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public  void update(Player player) {
        performAction(player);
    }

    protected abstract void performAction(Player player);



}
