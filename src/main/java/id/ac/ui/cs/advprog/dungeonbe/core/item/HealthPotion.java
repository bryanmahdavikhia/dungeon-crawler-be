package id.ac.ui.cs.advprog.dungeonbe.core.item;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import lombok.Getter;

@Getter
public class HealthPotion implements Item {

    private static final int ID = 0;
    private static final String NAME = "Health potion";
    private static final int BASE_HEALTH_GAIN = 100;
    private static final int BASE_PRICE = 100;
    private final int healthGain;
    private final int price;

    public HealthPotion(int dungeonLevel) {
        this.price = BASE_PRICE * (dungeonLevel+1);
        this.healthGain = BASE_HEALTH_GAIN * (dungeonLevel+1);
    }

    @Override
    public String useItem(Hero hero){
        int currentHealth = hero.getHealth();
        hero.setHealth(currentHealth + healthGain);
        return "Replenished health by " + healthGain;
    }

    @Override
    public int getId() {
        return ID;
    }

    @Override
    public String toString() {
        return NAME;
    }

    public static int calcPrice(int dungeonLevel) {
        return BASE_PRICE * (dungeonLevel+1);
    }
}
