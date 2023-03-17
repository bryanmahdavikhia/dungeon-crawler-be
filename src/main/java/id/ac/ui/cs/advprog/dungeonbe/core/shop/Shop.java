package id.ac.ui.cs.advprog.dungeonbe.core.shop;

import id.ac.ui.cs.advprog.dungeonbe.core.equipment.ArmorEquipment;
import id.ac.ui.cs.advprog.dungeonbe.core.equipment.WeaponEquipment;
import id.ac.ui.cs.advprog.dungeonbe.core.item.HealthPotion;
import id.ac.ui.cs.advprog.dungeonbe.core.item.ManaPotion;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private static final Shop uniqueInstance = new Shop();
    private static final String NOT_ENOUGH_GOLD = "Not enough gold to buy";
    private static final HealthPotion SAMPLE_HEALTH_POTION = new HealthPotion(0);
    private static final ManaPotion SAMPLE_MANA_POTION = new ManaPotion(0);

    private Shop() {}

    public static Shop getInstance() {
        return uniqueInstance;
    }

    private void addItemToAccount(Account user, String itemId) {
        if (user.getItems().equals("")) {
            user.setItems(itemId);
        } else {
            user.setItems(user.getItems()+","+itemId);
        }
    }

    private void buyHealthPotion(Account user, String itemId) throws IllegalStateException {
        if (user.getGold() < HealthPotion.calcPrice(user.getDungeonLevel())) {
            throw new IllegalStateException(NOT_ENOUGH_GOLD);
        }
        user.setGold(user.getGold()-HealthPotion.calcPrice(user.getDungeonLevel()));
        addItemToAccount(user, itemId);
    }

    private void buyManaPotion(Account user, String itemId) throws IllegalStateException {
        if (user.getGold() < ManaPotion.calcPrice(user.getDungeonLevel())) {
            throw new IllegalStateException(NOT_ENOUGH_GOLD);
        }
        user.setGold(user.getGold()-ManaPotion.calcPrice(user.getDungeonLevel()));
        addItemToAccount(user, itemId);
    }

    private void upgradeArmor(Account user) throws IllegalStateException {
        if (user.getGold() < ArmorEquipment.calcPrice(user.getArmorLevel())) {
            throw new IllegalStateException(NOT_ENOUGH_GOLD);
        }
        user.setGold(user.getGold()-ArmorEquipment.calcPrice(user.getArmorLevel()));
        user.setArmorLevel(user.getArmorLevel()+1);
    }

    private void upgradeWeapon(Account user) throws IllegalStateException {
        if (user.getGold() < WeaponEquipment.calcPrice(user.getWeaponLevel())) {
            throw new IllegalStateException(NOT_ENOUGH_GOLD);
        }
        user.setGold(user.getGold()-WeaponEquipment.calcPrice(user.getWeaponLevel()));
        user.setWeaponLevel(user.getWeaponLevel()+1);
    }

    public void addItemEquipment(Account user, String itemId) {
        if (itemId.equals(String.valueOf(SAMPLE_HEALTH_POTION.getId()))) {
            buyHealthPotion(user, itemId);
        } else if (itemId.equals(String.valueOf(SAMPLE_MANA_POTION.getId()))) {
            buyManaPotion(user, itemId);
        } else if (itemId.equals(String.valueOf(ArmorEquipment.getId()))) {
            upgradeArmor(user);
        } else if (itemId.equals(String.valueOf(WeaponEquipment.getId()))) {
            upgradeWeapon(user);
        } else {
            throw new IllegalArgumentException("Invalid item Id");
        }
    }


    public List<String> getListItemId() {
        List<String> listId = new ArrayList<>();
        listId.add(String.valueOf(SAMPLE_HEALTH_POTION.getId()));
        listId.add(String.valueOf(SAMPLE_MANA_POTION.getId()));
        listId.add(String.valueOf(ArmorEquipment.getId()));
        listId.add(String.valueOf(WeaponEquipment.getId()));

        return listId;
    }

    public List<String> getListItemName() {
        List<String> listName = new ArrayList<>();
        listName.add(String.valueOf(SAMPLE_HEALTH_POTION));
        listName.add(String.valueOf(SAMPLE_MANA_POTION));
        listName.add(String.valueOf(ArmorEquipment.getShopName()));
        listName.add(String.valueOf(WeaponEquipment.getShopName()));

        return listName;
    }

    public List<String> getListItemPrice(Account user) {
        List<String> listPrice = new ArrayList<>();
        listPrice.add(String.valueOf(HealthPotion.calcPrice(user.getDungeonLevel())));
        listPrice.add(String.valueOf(ManaPotion.calcPrice(user.getDungeonLevel())));
        listPrice.add(String.valueOf(ArmorEquipment.calcPrice(user.getArmorLevel())));
        listPrice.add(String.valueOf(WeaponEquipment.calcPrice(user.getWeaponLevel())));

        return listPrice;
    }
}
