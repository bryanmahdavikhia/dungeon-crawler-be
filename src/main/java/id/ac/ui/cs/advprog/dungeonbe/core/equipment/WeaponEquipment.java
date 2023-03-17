package id.ac.ui.cs.advprog.dungeonbe.core.equipment;

import lombok.Getter;

@Getter
public class WeaponEquipment implements Weapon {
    private static final int ID = 3;
    private static final String SHOP_NAME = "Upgrade Weapon";
    private static final int BASE_PRICE = 200;
    private int attack;
    private int bonusAttribute;
    private int level;

    public WeaponEquipment(int level){
        this.level = level;
        this.attack = 25;
        this.bonusAttribute = 10;
    }

    @Override
    public String upgrade() {
        this.level += 1;
        return "Weapon has been upgraded";
    }

    @Override
    public int getPrice() { return BASE_PRICE * (level+1); }

    @Override
    public int getAttack() {
        return attack*level;
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
