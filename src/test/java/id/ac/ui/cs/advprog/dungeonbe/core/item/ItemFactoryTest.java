package id.ac.ui.cs.advprog.dungeonbe.core.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemFactoryTest {

    ItemFactory itemFactory;

    @BeforeEach
    void setUp() throws Exception {
        itemFactory = new ItemFactory();
    }

    @Test
    void whenCreateItemAvailableShouldReturnHealthPotionClass() throws Exception {
        Class<?> classType = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.item.HealthPotion");
        Item instance = itemFactory.createItem(0, 0);
        assertEquals(instance.getClass(), classType);
    }

    @Test
    void whenCreateItemAvailableShouldReturnManaPotionClass() throws Exception {
        Class<?> classType = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.item.ManaPotion");
        Item instance = itemFactory.createItem(1, 0);
        assertEquals(instance.getClass(), classType);
    }

    @Test
    void whenCreateItemWhereIdNotExist() throws Exception {
        Item instance = itemFactory.createItem(2, 0);
        assertEquals(null, instance);
    }

}
