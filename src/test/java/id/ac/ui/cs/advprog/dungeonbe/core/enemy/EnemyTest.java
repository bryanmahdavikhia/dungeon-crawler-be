package id.ac.ui.cs.advprog.dungeonbe.core.enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnemyTest {
    private Class<?> enemyInterface;

    @BeforeEach
    void setUp() throws Exception{
        enemyInterface = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy");
    }

    @Test
    void testEnemyIsAbstractClass(){
        assertTrue(Modifier.isAbstract(enemyInterface.getModifiers()));
        assertFalse(Modifier.isInterface(enemyInterface.getModifiers()));
    }
}
