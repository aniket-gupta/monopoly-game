package monopolygame;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class PropertyTest {

    @Test
    public void testPropertyAttributes() {
        Property property = new Property("ABC", 200, Color.Red, new int[] {100,
                200,300});
        assertEquals("ABC", property.getName());
        assertEquals(200, property.getCost());
        assertEquals(Color.Red, property.getColor());
        assertEquals(-1, property.currentRentLevel());
        assertEquals(null, property.getOwner());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullRentLevel() {
        Property property = new Property("ABC", 200, Color.Red, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyRentLevel() {
        Property property = new Property("ABC", 200, Color.Red, new int[]{});
    }

    @Test
    public void testPerformAction() {
        List<Property> propertyList = Arrays.asList(
                new Property("a", 500, Color.Red, new int[]{100, 200, 300, 400, 500}),
                new Property("b", 600, Color.Red, new int[]{100, 200, 300,
                        400, 500}),
                new Property("c", 700, Color
                        .Red, new
                        int[]{100, 200, 300, 400, 500}),
                new Property("d", 200, Color
                        .Red, new
                        int[]{100, 200, 300, 400, 500}));
        Player player = new Player("ABC");
        for (Property property : propertyList) {
            int currentBalance = player.getMoney();
            property.playerLanded(player);

            assertEquals(currentBalance - property.getCost(), player
                    .getMoney());
            assertEquals(true, player.getOwnedProperty().contains(property));
            assertEquals(player, property.getOwner());
        }
    }

}