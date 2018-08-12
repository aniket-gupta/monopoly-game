package monopolygame;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SpecialTileTest {

    @Test
    public void testNoExcepWhenPlayerDontownPropertyAndOpponentDontHaveProp() {
        Player playerA = new Player("A");
        Player playerB = new Player("B");
        Tile specialTile = new SpecialTile(Arrays.asList(playerA, playerB));
        specialTile.playerLanded(playerA);
        specialTile.playerLanded(playerB);
    }

    @Test
    public void testWhenPlayerDontOwnPropertyAndOpponentHaveProp() {
        Player playerA = new Player("A");
        Player playerB = new Player("B");
        Tile specialTile = new SpecialTile(Arrays.asList(playerA, playerB));
        Property property1 = new Property("a", 500, Color.Red, new int[]{100,
                200, 300, 400, 500});
        Property property2 = new Property("b", 500, Color.Red, new int[]{100,
                200, 300, 400, 500});
        Property property3 = new Property("c", 600, Color.Red, new int[]{100,
                200, 300, 400, 500});
        playerB.buyProperty(property1);
        playerB.buyProperty(property2);
        specialTile.playerLanded(playerA);
        Assert.assertEquals(0,property1.currentRentLevel());
        Assert.assertEquals(0,property2.currentRentLevel());
        playerA.payRent(property1);
        Assert.assertEquals(1,property1.currentRentLevel());
        specialTile.playerLanded(playerA);
        Assert.assertEquals(0,property1.currentRentLevel());
        Assert.assertEquals(0,property2.currentRentLevel());
        playerB.buyProperty(property3);
        playerA.payRent(property3);
        playerA.payRent(property3);
        specialTile.playerLanded(playerA);
        Assert.assertEquals(0,property1.currentRentLevel());
        Assert.assertEquals(0,property2.currentRentLevel());
        Assert.assertEquals(1,property3.currentRentLevel());

    }

    @Test
    public void testWhenPlayerOwnPropertyAndOpponentDontHaveProp() {
        Player playerA = new Player("A");
        Player playerB = new Player("B");
        Tile specialTile = new SpecialTile(Arrays.asList(playerA, playerB));
        Property property1 = new Property("a", 500, Color.Red, new int[]{100,
                200, 300, 400, 500});
        Property property2 = new Property("b", 500, Color.Red, new int[]{100,
                200, 300, 400, 500});
        Property property3 = new Property("c", 600, Color.Red, new int[]{100,
                200, 300, 400, 500});
        playerA.buyProperty(property1);
        playerA.buyProperty(property2);
        specialTile.playerLanded(playerA);
        Assert.assertEquals(1,property1.currentRentLevel());
        Assert.assertEquals(0,property2.currentRentLevel());
        playerB.payRent(property1);
        Assert.assertEquals(2,property1.currentRentLevel());
        specialTile.playerLanded(playerA);
        Assert.assertEquals(3,property1.currentRentLevel());
        Assert.assertEquals(0,property2.currentRentLevel());
        playerA.buyProperty(property3);
        playerB.payRent(property3);
        playerB.payRent(property3);
        specialTile.playerLanded(playerA);
        Assert.assertEquals(3,property1.currentRentLevel());
        Assert.assertEquals(0,property2.currentRentLevel());
        Assert.assertEquals(3,property3.currentRentLevel());
    }

    @Test
    public void testWhenPlayerOwnPropertyAndOpponentOwnHaveProp() {
        Player playerA = new Player("A");
        Player playerB = new Player("B");
        Tile specialTile = new SpecialTile(Arrays.asList(playerA, playerB));
        Property property1 = new Property("a", 500, Color.Red, new int[]{100,
                200, 300, 400, 500});
        Property property2 = new Property("b", 400, Color.Red, new int[]{100,
                200, 300, 400, 500});
        Property property3 = new Property("c", 600, Color.Red, new int[]{100,
                200, 300, 400, 500});
        Property property4 = new Property("d", 700, Color.Red, new int[]{100,
                200, 300, 400, 500});
        playerA.buyProperty(property1);
        playerA.buyProperty(property2);
        playerB.buyProperty(property3);
        playerB.buyProperty(property4);
        specialTile.playerLanded(playerA);
        Assert.assertEquals(1,property1.currentRentLevel());
        Assert.assertEquals(0,property2.currentRentLevel());
        playerB.payRent(property1);
        Assert.assertEquals(2,property1.currentRentLevel());
        specialTile.playerLanded(playerA);
        Assert.assertEquals(3,property1.currentRentLevel());
        Assert.assertEquals(0,property2.currentRentLevel());

        Assert.assertEquals(0,property3.currentRentLevel());
        Assert.assertEquals(0,property4.currentRentLevel());
        specialTile.playerLanded(playerB);
        Assert.assertEquals(0,property3.currentRentLevel());
        Assert.assertEquals(1,property4.currentRentLevel());
        Assert.assertEquals(2,property1.currentRentLevel());
        Assert.assertEquals(0,property2.currentRentLevel());

    }
}