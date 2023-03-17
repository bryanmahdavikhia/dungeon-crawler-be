package id.ac.ui.cs.advprog.dungeonbe.core.item;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HealthPotionTest {

    private Class<?> healthPotionClass;
    private Class<?> accountClass;
    private Hero hero;

    @Mock
    private Account mockAccount;

    @BeforeEach
    void setUp() throws Exception {
        healthPotionClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.item.HealthPotion");
        accountClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.model.Account");
        mockAccount = (Account) mock(accountClass);

        hero = new Hero(mockAccount);
    }

    @Test
    void testHealthPotionIsConcreteClass() {
        assertFalse(Modifier.isAbstract(healthPotionClass.getModifiers()));
    }

    @Test
    void tesHealthPotionIsAnItem() {
        Collection<Type> interfaces = Arrays.asList(healthPotionClass.getInterfaces());

        assertTrue(interfaces.stream()
                .anyMatch(type -> type.getTypeName()
                        .equals("id.ac.ui.cs.advprog.dungeonbe.core.item.Item")));
    }

    @Test
    void testHealthPotionOverrideUseItemMethod() throws Exception {
        Class<?>[] countItemArgs = new Class[1];
        countItemArgs[0] = Hero.class;
        Method useItem = healthPotionClass.getDeclaredMethod("useItem", countItemArgs);
        HealthPotion healthPotion = new HealthPotion(0);

        healthPotion.useItem(hero);

        assertEquals("java.lang.String",
                useItem.getGenericReturnType().getTypeName());
        assertEquals(1,
                useItem.getParameterCount());
        assertTrue(Modifier.isPublic(useItem.getModifiers()));
    }

    @Test
    void testUseItemShouldReturnCorrectly() {
        HealthPotion healthPotion = new HealthPotion(0);

        String useItem = healthPotion.useItem(hero);

        assertEquals("Replenished health by 100", useItem);
    }

    @Test
    void testHealthPotionToString() {
        HealthPotion healthPotion = new HealthPotion(0);
        assertEquals("Health potion", healthPotion.toString());
    }
}
