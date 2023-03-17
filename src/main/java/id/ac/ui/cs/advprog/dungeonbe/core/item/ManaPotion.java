package id.ac.ui.cs.advprog.dungeonbe.core.item;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import lombok.Getter;

@Getter
public class ManaPotion implements Item {

    private static final int ID = 1;
    private static final String NAME = "Mana potion";
    private static final int BASE_MANA_GAIN = 100;
    private static final int BASE_PRICE = 80;
    private final int manaGain;
    private final int price;

    public ManaPotion(int dungeonLevel) {
        this.price = BASE_PRICE * (dungeonLevel+1);
        this.manaGain = BASE_MANA_GAIN * (dungeonLevel+1);
    }

    @Override
    public String useItem(Hero hero){
        int currentMana = hero.getMana();
        hero.setMana(currentMana + manaGain);
        return "Replenished mana by " + manaGain;
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
