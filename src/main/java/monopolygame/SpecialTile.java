package monopolygame;

import java.util.ArrayList;
import java.util.List;

public class SpecialTile extends Tile {

    private List<Player> players;

    public SpecialTile(List<Player> players) {
        super("Special", Color.None);
        this.players = players;
    }

    private void decrRentLevelForOpponents(Player player) {
        List<Player> opponents = getOpponentsOf(player);
        for (Player opponent : opponents) {
            decrRentLevel(opponent);
        }
    }

    private List<Player> getOpponentsOf(Player currPlayer) {
        ArrayList<Player> opponents = new ArrayList<>();
        for(Player player : players) {
            if(!player.equals(currPlayer)) {
                opponents.add(player);
            }
        }
        return opponents;
    }

    private void incrRentLevel(Player player) {
        Property targetProperty = getHighestValuePropertyOfPlayer(player);
        if(targetProperty != null)
            targetProperty.incRentLevel();
    }

    private Property getHighestValuePropertyOfPlayer(Player player) {
        List<Property> properties = player.getOwnedProperty();
        int max = Integer.MIN_VALUE;
        Property targetProperty = null;
        for (Property property : properties) {
            if(max < property.getCost()) {
                max= property.getCost();
                targetProperty = property;
            }
        }
        return targetProperty;
    }

    private void decrRentLevel(Player player) {
        Property targetProperty = getHighestValuePropertyOfPlayer(player);
        if(targetProperty != null)
            targetProperty.decRentLevel();
    }


    @Override
    protected void performAction(Player player) {
        incrRentLevel(player);
        decrRentLevelForOpponents(player);
    }
}