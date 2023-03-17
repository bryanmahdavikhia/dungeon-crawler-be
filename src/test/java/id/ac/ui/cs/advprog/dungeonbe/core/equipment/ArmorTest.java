package id.ac.ui.cs.advprog.dungeonbe.core.equipment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ArmorTest {
    private Class<?> armorClass;

    @BeforeEach
    void setup() throws Exception{
        armorClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.equipment.Armor");
    }

    @Test
    void testArmorIsAPublicInterface() {
        int classModifiers = armorClass.getModifiers();

        assertTrue(Modifier.isPublic(classModifiers));
        assertTrue(Modifier.isInterface(classModifiers));
    }

}
