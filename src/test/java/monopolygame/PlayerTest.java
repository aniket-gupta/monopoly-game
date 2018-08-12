package monopolygame;

import monopolygame.exception.InsufficientBalanceException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PlayerTest {

    @Test
    public void testPlayerSeededWith2000$() {
        Player player = new Player("ABC");
        Assert.assertEquals("ABC", player.getName());
        Assert.assertEquals(2000, player.getMoney());
    }

    @Test
    public void testPLayerCollect200OnCrossingGo() {
        Player player = new Player("ABC");
        Tile go = new GO();
        go.playerLanded(player);
        Assert.assertEquals(2200, player.getMoney());
    }

    @Test
    public void testPlayerCanBuyUnownedPropertyWhenHasEnoughMoney() {

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
            player.buyProperty(property);
            Assert.assertEquals(currentBalance - property.getCost(), player
                    .getMoney());
            Assert.assertEquals(true, player.getOwnedProperty().contains(property));
        }
    }

    @Test
    public void testPlayerCantBuyUnownedPropertyWhenDonotHaveEnoughMoney() {
        List<Property> propertyList = Arrays.asList(
                new Property("a", 500, Color.Red, new int[]{100, 200, 300, 400, 500}),
                new Property("b", 600, Color.Red, new int[]{100, 200, 300,
                        400, 500}),
                new Property("c", 700, Color
                        .Red, new
                        int[]{100, 200, 300, 400, 500}),
                new Property("d", 500, Color
                        .Red, new
                        int[]{100, 200, 300, 400, 500}));
        Player player = new Player("ABC");

        for (int i = 0; i < propertyList.size() - 1; i++) {
            int currentBalance = player.getMoney();
            player.buyProperty(propertyList.get(i));
            Assert.assertEquals(currentBalance - propertyList.get(i).getCost(), player
                    .getMoney());
            Assert.assertEquals(true, player.getOwnedProperty().contains(propertyList.get(i)));
        }
        int expBal = player.getMoney();
        player.buyProperty(propertyList.get(propertyList.size()-1));
        Assert.assertEquals(expBal, player
                .getMoney());
        Assert.assertEquals(false, player.getOwnedProperty().contains
                (propertyList.get(propertyList.size()-1)));

    }

    @Test
    public void testCantBuyOwnedProperty() {
        Property property = new Property("a", 500, Color.Red, new int[]{100,
                200, 300,
                400, 500});
        Player player = new Player("ABC");
        player.buyProperty(property);
        player.buyProperty(property);
        Assert.assertEquals(1, player.getOwnedProperty().size());
        Assert.assertEquals(2000 - property.getCost(), player.getMoney());
    }

    @Test
    public void testPayRentWhenHaveEnoughMoney() {
        Player playerA = new Player("ABC");
        Player playerB = new Player("XYZ");

        Property property = new Property("a", 500, Color.Red, new int[]{100,
                200, 300,
                400, 500});
        playerA.buyProperty(property);
        int expBalB = playerB.getMoney() - property.currentRent();
        int expBalA = playerA.getMoney() + property.currentRent();
        playerB.payRent(property);
        Assert.assertEquals(expBalB, playerB.getMoney());
        Assert.assertEquals(expBalA, playerA.getMoney());

    }

    @Test(expected = InsufficientBalanceException.class)
    public void testPayRentWhenHaveDontHaveEnoughMoney() {

        Player playerA = new Player("ABC");
        Player playerB = new Player("XYZ");

        Property property = new Property("a", 500, Color.Red, new int[]{100,
                200, 300,
                400, 500});
        playerA.buyProperty(property);
        int expBalB = playerB.getMoney() - property.currentRent();
        int expBalA = playerA.getMoney() + property.currentRent();
        playerB.payRent(property);
        Assert.assertEquals(expBalB, playerB.getMoney());
        Assert.assertEquals(expBalA, playerA.getMoney());
        playerB.payRent(property);
        playerB.payRent(property);
        playerB.payRent(property);
        playerB.payRent(property);
        playerB.payRent(property);
        playerB.payRent(property);
    }

    @Test
    public void testLandOnUnownedProperty() {
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
            player.landOn(property);
            Assert.assertEquals(currentBalance - property.getCost(), player
                    .getMoney());
            Assert.assertEquals(true, player.getOwnedProperty().contains(property));
        }
    }

    @Test
    public void testLandOnSoldProperty() {
        Player playerA = new Player("ABC");
        Player playerB = new Player("XYZ");

        Property property = new Property("a", 500, Color.Red, new int[]{100,
                200, 300,
                400, 500});
        playerA.buyProperty(property);
        int expBalB = playerB.getMoney() - property.currentRent();
        int expBalA = playerA.getMoney() + property.currentRent();
        playerB.landOn(property);
        Assert.assertEquals(expBalB, playerB.getMoney());
        Assert.assertEquals(expBalA, playerA.getMoney());
    }

}