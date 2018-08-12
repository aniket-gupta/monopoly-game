package monopolygame;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GOTest {

    @Test
    public void testPlayerCollectMoneyWhenCrossesGO() {
        Player player = new Player("A");
        int bal = player.getMoney();
        Tile go = new GO();
        go.playerLanded(player);
        Assert.assertEquals(bal = (bal + 200), player.getMoney());
        go.playerLanded(player);
        Assert.assertEquals(bal = (bal + 200), player.getMoney());
        go.playerLanded(player);
        Assert.assertEquals(bal = (bal + 200), player.getMoney());
        go.playerLanded(player);
        Assert.assertEquals(bal = (bal + 200), player.getMoney());
    }
}