package monopolygame;

public abstract class Tile {

    private String name;

    public Tile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void update(Player player);



}
