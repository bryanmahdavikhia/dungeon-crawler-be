package id.ac.ui.cs.advprog.dungeonbe.core.equipment;

import lombok.Getter;

@Getter
public class ArmorEquipment implements Armor{
    private static final int ID = 2;
    private static final String SHOP_NAME = "Upgrade Armor";
    private static final int BASE_PRICE = 150;
    private int defense;
    private int bonusAttribute;
    private int level;

    public ArmorEquipment(int level){
        this.level = level;
        this.defense = 20;
        this.bonusAttribute = 10;
    }

    @Override
    public String upgrade() {
        this.level += 1;
        return "Armor has been upgraded";
    }

    @Override
    public int getPrice() { return BASE_PRICE * (level+1); }

    @Override
    public int getDefense() {
        return defense*level;
    }

    @Override
    public int getBonusAttribute() {
        return bonusAttribute * level;
    }

    public static int getId() {
        return ID;
    }

    public static String getShopName() {
        return SHOP_NAME;
    }

    public static int calcPrice(int level) {
        return BASE_PRICE * (level+1);
    }
}
