package id.ac.ui.cs.advprog.dungeonbe.core.shop;

import id.ac.ui.cs.advprog.dungeonbe.core.equipment.ArmorEquipment;
import id.ac.ui.cs.advprog.dungeonbe.core.equipment.WeaponEquipment;
import id.ac.ui.cs.advprog.dungeonbe.core.item.HealthPotion;
import id.ac.ui.cs.advprog.dungeonbe.core.item.ManaPotion;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShopTest {

    private Shop shop;
    private Account sampleAccount;
    private HealthPotion sampleHealthPotion;
    private ManaPotion sampleManaPotion;

    @BeforeEach
    void setUp(){
        shop = Shop.getInstance();
        sampleAccount = new Account();
        sampleAccount.setMaxHealth(100);
        sampleAccount.setMaxMana(100);
        sampleAccount.setAttack(80);
        sampleAccount.setDefense(50);
        sampleAccount.setExp(0);
        sampleAccount.setGold(0);
        sampleAccount.setLevel(1);
        sampleAccount.setDungeonLevel(0);
        sampleAccount.setWeaponLevel(0);
        sampleAccount.setArmorLevel(0);
        sampleAccount.setItems("");
        sampleHealthPotion = new HealthPotion(0);
        sampleManaPotion = new ManaPotion(0);
    }

    @Test
    void testGetInstanceShouldReturnSameObject() {
        Shop anotherShop = Shop.getInstance();
        assert shop == anotherShop;
        assertEquals(shop, anotherShop);
    }

    @Test
    void testAddItemEquipmentWhenItemIdIsHealthPotionAndNotHaveEnoughGold() {
        try {
            shop.addItemEquipment(sampleAccount, String.valueOf(sampleHealthPotion.getId()));
        } catch (RuntimeException e) {
            assertEquals("Not enough gold to buy", e.getMessage());
        }
    }

    @Test
    void testAddItemEquipmentWhenItemIdIsHealthPotionAndHaveEnoughGold() {
        sampleAccount.setGold(200);
        try {
            shop.addItemEquipment(sampleAccount, String.valueOf(sampleHealthPotion.getId()));
        } catch (RuntimeException e) {
            fail();
        }

        assertEquals(100, sampleAccount.getGold());
        assertEquals("0", sampleAccount.getItems());
    }

    @Test
    void testAddItemEquipmentWhenItemIdIsHealthPotionAndHaveEnoughGoldAndAlreadyHaveItem() {
        sampleAccount.setItems("1");
        sampleAccount.setGold(200);
        try {
            shop.addItemEquipment(sampleAccount, String.valueOf(sampleHealthPotion.getId()));
        } catch (RuntimeException e) {
            fail();
        }

        assertEquals(100, sampleAccount.getGold());
        assertEquals("1,0", sampleAccount.getItems());
    }

    @Test
    void testAddItemEquipmentWhenItemIdIsManaPotionAndNotHaveEnoughGold() {
        try {
            shop.addItemEquipment(sampleAccount, String.valueOf(sampleManaPotion.getId()));
        } catch (RuntimeException e) {
            assertEquals("Not enough gold to buy", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testAddItemEquipmentWhenItemIdIsManaPotionAndHaveEnoughGold() {
        sampleAccount.setGold(200);
        try {
            shop.addItemEquipment(sampleAccount, String.valueOf(sampleManaPotion.getId()));
        } catch (RuntimeException e) {
            fail();
        }

        assertEquals(120, sampleAccount.getGold());
        assertEquals("1", sampleAccount.getItems());
    }

    @Test
    void testAddItemEquipmentWhenItemIdIsArmorAndNotHaveEnoughGold() {
        try {
            shop.addItemEquipment(sampleAccount, String.valueOf(ArmorEquipment.getId()));
        } catch (RuntimeException e) {
            assertEquals("Not enough gold to buy", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testAddItemEquipmentWhenItemIdIsArmorAndHaveEnoughGold() {
        sampleAccount.setGold(200);
        try {
            shop.addItemEquipment(sampleAccount, String.valueOf(ArmorEquipment.getId()));
        } catch (RuntimeException e) {
            fail();
        }

        assertEquals(50, sampleAccount.getGold());
        assertEquals(1, sampleAccount.getArmorLevel());
    }

    @Test
    void testAddItemEquipmentWhenItemIdIsWeaponAndNotHaveEnoughGold() {
        try {
            shop.addItemEquipment(sampleAccount, String.valueOf(WeaponEquipment.getId()));
        } catch (RuntimeException e) {
            assertEquals("Not enough gold to buy", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testAddItemEquipmentWhenItemIdIsWeaponAndHaveEnoughGold() {
        sampleAccount.setGold(200);
        try {
            shop.addItemEquipment(sampleAccount, String.valueOf(WeaponEquipment.getId()));
        } catch (RuntimeException e) {
            fail();
        }

        assertEquals(0, sampleAccount.getGold());
        assertEquals(1, sampleAccount.getWeaponLevel());
    }

    @Test
    void testAddItemEquipmentWhenItemIdIsWrong() {
        try {
            shop.addItemEquipment(sampleAccount, "-1");
        } catch (RuntimeException e) {
            assertEquals("Invalid item Id", e.getMessage());
            return;
        }
        fail();
    }

    @Test
    void testGetListItemId() {
        List<String> listExpectedId = new ArrayList<>();
        listExpectedId.add("0");
        listExpectedId.add("1");
        listExpectedId.add("2");
        listExpectedId.add("3");

        assertEquals(listExpectedId, shop.getListItemId());
    }

    @Test
    void testGetListItemName() {
        List<String> listExpectedId = new ArrayList<>();
        listExpectedId.add("Health potion");
        listExpectedId.add("Mana potion");
        listExpectedId.add("Upgrade Armor");
        listExpectedId.add("Upgrade Weapon");

        assertEquals(listExpectedId, shop.getListItemName());
    }

    @Test
    void testGetListItemPrice() {
        List<String> listExpectedId = new ArrayList<>();
        listExpectedId.add("100");
        listExpectedId.add("80");
        listExpectedId.add("150");
        listExpectedId.add("200");

        assertEquals(listExpectedId, shop.getListItemPrice(sampleAccount));
    }
}
