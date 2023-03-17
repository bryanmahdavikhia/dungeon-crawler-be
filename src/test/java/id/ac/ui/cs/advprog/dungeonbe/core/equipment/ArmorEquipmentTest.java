package id.ac.ui.cs.advprog.dungeonbe.core.equipment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArmorEquipmentTest {
    private Class<?> armorEquipmentClass;

    @BeforeEach
    void setUp() throws Exception{
        armorEquipmentClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.equipment.ArmorEquipment");
    }

    @Test
    void testArmorEquipmentIsConcreteClass() {
        assertFalse(Modifier.isAbstract(armorEquipmentClass.getModifiers()));
    }

    @Test
    void testArmorEquipmentIsAnArmor() {
        Collection<Type> interfaces = Arrays.asList(armorEquipmentClass.getInterfaces());

        assertTrue(interfaces.stream()
                .anyMatch(type -> type.getTypeName()
                        .equals("id.ac.ui.cs.advprog.dungeonbe.core.equipment.Armor")));
    }

    @Test
    void testArmorEquipmentOverideUpgradeMethod() throws Exception {
        Method upgrade = armorEquipmentClass.getDeclaredMethod("upgrade");
        ArmorEquipment armorEquipment = mock(ArmorEquipment.class);

        armorEquipment.upgrade();

        verify(armorEquipment, times(1)).upgrade();
        assertEquals("java.lang.String",
                upgrade.getGenericReturnType().getTypeName());
        assertEquals(0,
                upgrade.getParameterCount());
    }

    @Test
    void testUpgradeShouldReturnCorrectly() {
        ArmorEquipment armorEquipment = new ArmorEquipment(0);

        String upgrade = armorEquipment.upgrade();

        assertEquals("Armor has been upgraded", upgrade);
    }

    @Test
    void testGetDefenseShouldReturnCorrectly() {
        ArmorEquipment armorEquipment = new ArmorEquipment(0);

        int getDefense = armorEquipment.getDefense();

        assertEquals(0, getDefense);
    }

    @Test
    void testGetPriceShouldReturnCorrectly() {
        ArmorEquipment armorEquipment = new ArmorEquipment(0);
        int getPrice = armorEquipment.getPrice();
        assertEquals(150, getPrice);
    }

    @Test
    void testIdShouldHasBeenUpgraded() {
        ArmorEquipment armorEquipment = new ArmorEquipment(0);

        armorEquipment.upgrade();

        assertEquals(2,armorEquipment.getId());
        assertEquals(20, armorEquipment.getDefense());
        assertEquals(10, armorEquipment.getBonusAttribute());
    }

    @Test
    void testGetShopName() {
        assertEquals("Upgrade Armor", ArmorEquipment.getShopName());
    }

    @Test
    void testCalcPrice() {
        assertEquals(300, ArmorEquipment.calcPrice(1));
    }
}
