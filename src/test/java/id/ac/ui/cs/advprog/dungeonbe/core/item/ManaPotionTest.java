package id.ac.ui.cs.advprog.dungeonbe.core.item;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ManaPotionTest {

    private Class<?> manaPotionClass;
    private Class<?> accountClass;

    @Mock
    private Hero hero;

    @Mock
    private Account mockAccount;

    @BeforeEach
    void setUp() throws Exception {
        manaPotionClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.item.ManaPotion");
        accountClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.model.Account");
        mockAccount = (Account) mock(accountClass);

        mockAccount.setItems("");
    }

    @Test
    void testManaPotionIsConcreteClass() {
        assertFalse(Modifier.isAbstract(manaPotionClass.getModifiers()));
    }

    @Test
    void testManaPotionIsAnItem() {
        Collection<Type> interfaces = Arrays.asList(manaPotionClass.getInterfaces());

        assertTrue(interfaces.stream()
                .anyMatch(type -> type.getTypeName()
                        .equals("id.ac.ui.cs.advprog.dungeonbe.core.item.Item")));
    }

    @Test
    void testManaPotionOverrideUseItemMethod() throws Exception {
        hero = new Hero(mockAccount);
        List<Item> item = new ArrayList<>();

        Class<?>[] countItemArgs = new Class[1];
        countItemArgs[0] = Hero.class;
        Method useItem = manaPotionClass.getDeclaredMethod("useItem", countItemArgs);
        ManaPotion manaPotion = new ManaPotion(0);

        manaPotion.useItem(hero);

        assertEquals("java.lang.String",
                useItem.getGenericReturnType().getTypeName());
        assertEquals(1,
                useItem.getParameterCount());
        assertTrue(Modifier.isPublic(useItem.getModifiers()));
    }

    @Test
    void testUseItemShouldReturnCorrectly() {
        hero = new Hero(mockAccount);
        ManaPotion manaPotion = new ManaPotion(0);
        String useItem = manaPotion.useItem(hero);


        assertEquals("Replenished mana by 100", useItem);
    }

    @Test
    void testManaPotionOverrideGetIdMethod() throws Exception {
        Class<?>[] countItemArgs = new Class[0];
        Method getId = manaPotionClass.getDeclaredMethod("getId", countItemArgs);

        assertEquals("int",
                getId.getGenericReturnType().getTypeName());
        assertEquals(0,
                getId.getParameterCount());
        assertTrue(Modifier.isPublic(getId.getModifiers()));
    }

    @Test
    void testGetIdShouldReturnCorrectly() {
        ManaPotion manaPotion = new ManaPotion(0);
        int id = manaPotion.getId();

        assertEquals(1, id);
    }

    @Test
    void testManaPotionToString() {
        ManaPotion manaPotion = new ManaPotion(0);
        assertEquals("Mana potion", manaPotion.toString());
    }
}
