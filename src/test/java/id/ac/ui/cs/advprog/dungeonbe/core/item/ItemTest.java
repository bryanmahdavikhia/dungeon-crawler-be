package id.ac.ui.cs.advprog.dungeonbe.core.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertTrue;


class ItemTest {
    private Class<?> itemClass;

    @BeforeEach
    void setup() throws Exception {
        itemClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.item.Item");
    }

    @Test
    void testItemIsAPublicInterface() {
        int classModifiers = itemClass.getModifiers();

        assertTrue(Modifier.isPublic(classModifiers));
        assertTrue(Modifier.isInterface(classModifiers));
    }

}
