package monopolygame;

public abstract class Tile {

    private String name;

    public Tile(String name) {
        this.name = name;
    }

    public abstract void action(Player player);

    public String getName() {
        return name;
    }

}
