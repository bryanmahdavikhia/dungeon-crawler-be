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

class WeaponEquipmentTest {
    private Class<?> weaponEquipmentClass;

    @BeforeEach
    void setUp() throws Exception{
        weaponEquipmentClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.equipment.WeaponEquipment");
    }

    @Test
    void testArmorEquipmentIsConcreteClass() {
        assertFalse(Modifier.isAbstract(weaponEquipmentClass.getModifiers()));
    }

    @Test
    void testWeaponEquipmentIsAWeapon() {
        Collection<Type> interfaces = Arrays.asList(weaponEquipmentClass.getInterfaces());

        assertTrue(interfaces.stream()
                .anyMatch(type -> type.getTypeName()
                        .equals("id.ac.ui.cs.advprog.dungeonbe.core.equipment.Weapon")));
    }

    @Test
    void testWeaponEquipmentOverideUpgradeMethod() throws Exception {
        Method upgrade = weaponEquipmentClass.getDeclaredMethod("upgrade");
        WeaponEquipment weaponEquipment = mock(WeaponEquipment.class);

        weaponEquipment.upgrade();

        verify(weaponEquipment, times(1)).upgrade();
        assertEquals("java.lang.String",
                upgrade.getGenericReturnType().getTypeName());
        assertEquals(0,
                upgrade.getParameterCount());
    }

    @Test
    void testUpgradeShouldReturnCorrectly() throws Exception{
        WeaponEquipment weaponEquipment = new WeaponEquipment(0);

        String upgrade = weaponEquipment.upgrade();

        assertEquals("Weapon has been upgraded", upgrade);
    }

    @Test
    void testGetAttackShouldReturnCorrectly() throws Exception{
        WeaponEquipment weaponEquipment = new WeaponEquipment(0);

        int getAttack = weaponEquipment.getAttack();

        assertEquals(0, getAttack);
    }

    @Test
    void testGetPriceShouldReturnCorrectly() throws Exception{
        WeaponEquipment weaponEquipment = new WeaponEquipment(0);
        int getPrice = weaponEquipment.getPrice();
        assertEquals(200, getPrice);
    }

    @Test
    void testIdShouldHasBeenUpgraded() throws Exception{
        WeaponEquipment weaponEquipment = new WeaponEquipment(0);

        weaponEquipment.upgrade();

        assertEquals(3,weaponEquipment.getId());
        assertEquals(25, weaponEquipment.getAttack());
        assertEquals(10, weaponEquipment.getBonusAttribute());
    }

    @Test
    void testGetShopName() {
        assertEquals("Upgrade Weapon", WeaponEquipment.getShopName());
    }

    @Test
    void testCalcPrice() {
        assertEquals(400, WeaponEquipment.calcPrice(1));
    }
}
