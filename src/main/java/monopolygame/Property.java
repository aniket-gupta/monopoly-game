package monopolygame;

public class Property extends Tile {

    /**
     * 8. Property has the cost value
     * 9. Property has color
     * 10. Property has its rent levels (1-5).
     * 11. Rent levels, start at 1, go up by 1 right after a successful rent payment for a property.
     * 12. Special tile - increases the rent level by 1 for one of the highest value property for the player, decreases the rent level by 1 for the highest value property held by the opponents.
     * 13. Rent level cannot go lower than 1.
     */


    private int cost;
    private Color color;
    private Player owner;
    private int rentLevel;
    private int[] rentPerLevel;

    public Property(String name, int cost, Color color, int[] rentPerLevel) {
        super(name);
        this.cost = cost;
        this.color = color;

        this.rentPerLevel = new int[rentPerLevel.length];
        for(int i = 0; i < rentPerLevel.length; i++) {
            this.rentPerLevel[i] = rentPerLevel[i];
        }
        rentLevel = -1;
    }


    public void setOwner(Player player) {
        this.owner = player;
        rentLevel = 0;
    }

    public void incRentLevel() {
        if(rentLevel + 1 >= rentPerLevel.length) return;
        rentLevel++;
    }

    public void decRentLevel() {
        if(rentLevel - 1 < 0) return;
        rentLevel--;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    public int currentRent() {
        return rentPerLevel[rentLevel];
    }

    public Color getColor() {
        return color;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getName() + " {");
        stringBuilder.append("Owner: " + (owner == null ? "None (For sale)" :
                owner.getName()));
        stringBuilder.append(", Current Rent Level: " + (owner == null ?
                "N/A" : rentLevel + 1));
        stringBuilder.append(", Color: " + color);
        stringBuilder.append("}");
        return stringBuilder.toString();

    }

    @Override
    public void action(Player player) {
        player.landOn(this);
    }
}
