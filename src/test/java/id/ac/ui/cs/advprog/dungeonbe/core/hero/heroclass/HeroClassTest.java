package id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass;

import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeroClassTest {

    private Class<?> heroClassInterface;

    @BeforeEach
    void setUp() throws Exception{
        heroClassInterface = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClass");
    }

    @Test
    void testHeroClassIsPublicInterface(){
        assertTrue(Modifier.isPublic(heroClassInterface.getModifiers()));
        assertTrue(Modifier.isInterface(heroClassInterface.getModifiers()));
    }

    @Test
    void testHeroClassHasAbstractUseSkillMethod() throws Exception{
        Class<?>[] countItemArgs = new Class[2];
        countItemArgs[0] = Hero.class;
        countItemArgs[1] = Enemy.class;
        Method useSkill = heroClassInterface.getDeclaredMethod("useSkill", countItemArgs);

        assertTrue(Modifier.isPublic(useSkill.getModifiers()));
        assertTrue(Modifier.isAbstract(useSkill.getModifiers()));
        assertEquals(2, useSkill.getParameterCount());
    }

    @Test
    void testHeroClassHasAbstractGiveBonusFromEquipmentMethod() throws Exception{
        Class<?>[] countItemArgs = new Class[1];
        countItemArgs[0] = Hero.class;
        Method giveBonusFromEquipment = heroClassInterface.getDeclaredMethod("giveBonusFromEquipment", countItemArgs);

        assertTrue(Modifier.isPublic(giveBonusFromEquipment.getModifiers()));
        assertTrue(Modifier.isAbstract(giveBonusFromEquipment.getModifiers()));
        assertEquals(1, giveBonusFromEquipment.getParameterCount());
    }
}
