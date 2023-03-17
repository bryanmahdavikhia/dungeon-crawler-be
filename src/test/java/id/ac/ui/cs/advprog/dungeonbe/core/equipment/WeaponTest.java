package id.ac.ui.cs.advprog.dungeonbe.core.equipment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WeaponTest {
    private Class<?> weaponClass;

    @BeforeEach
    void setup() throws Exception{
        weaponClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.equipment.Weapon");
    }

    @Test
    void testWeaponIsAPublicInterface() {
        int classModifiers = weaponClass.getModifiers();

        assertTrue(Modifier.isPublic(classModifiers));
        assertTrue(Modifier.isInterface(classModifiers));
    }
}
