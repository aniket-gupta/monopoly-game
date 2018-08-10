package monopolygame;


import monopolygame.exception.AlreadySoldException;
import monopolygame.exception.InsufficientBalanceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

    private static final int INITIAL_BALANCE = 2000;

    private String name;
    private int money;
    private boolean isBankrupt;
    private List<Property> ownedProperties;

    public Player(String name) {
        this(name, INITIAL_BALANCE);
    }

    public Player(String name, int initialBalance) {
        this.name = name;
        this.money = initialBalance;
        ownedProperties = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void collect(int amount) {
        money += amount;
    }

    public void payRent(Property property) {
        if(money < property.currentRent())
            throw new InsufficientBalanceException();
        money -= property.currentRent();
        property.getOwner().collect(property.currentRent());
        property.incRentLevel();

    }

    public void buyProperty(Property property) {
        if(property.getOwner() != null)
            throw new AlreadySoldException();
        if(money < property.getCost())
            throw new InsufficientBalanceException();
        money -= property.getCost();
        property.setOwner(this);
        ownedProperties.add(property);
    }

    public void landOn(Property property) {
        if (property.hasOwner()) {
            payRent(property);
        } else {
            buyProperty(property);
        }
    }

    public void landOn(SpecialTile specialTile) {

    }

    public List<Property> getOwnedProperty() {
        return ownedProperties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + " - {Balance: $" + money + "}";
    }
}
