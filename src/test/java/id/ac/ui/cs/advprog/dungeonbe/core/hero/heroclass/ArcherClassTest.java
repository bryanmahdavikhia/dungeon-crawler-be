package id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass;

import id.ac.ui.cs.advprog.dungeonbe.core.enemy.BossMonster;
import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
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
import static org.mockito.Mockito.when;

class ArcherClassTest {

    private Class<?> archerClassClass;
    private Class<?> accountClass;

    @Mock
    Account mockAccount;

    @BeforeEach
    void setUp() throws Exception{
        archerClassClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.ArcherClass");
        accountClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.model.Account");
        mockAccount = (Account) mock(accountClass);
    }

    @Test
    void testArcherClassIsConcreteClass(){
        assertFalse(Modifier.isAbstract(archerClassClass.getModifiers()));
        assertFalse(Modifier.isInterface(archerClassClass.getModifiers()));
    }

    @Test
    void testArcherClassIsAHeroClass(){
        Collection<Type> interfaces = Arrays.asList(archerClassClass.getInterfaces());

        assertTrue(interfaces.stream()
                .anyMatch(type -> type.getTypeName()
                        .equals("id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClass")));
    }

    @Test
    void testArcherHeroHasUseSkillMethod () throws Exception{
        Class<?>[] countItemArgs = new Class[2];
        countItemArgs[0] = Hero.class;
        countItemArgs[1] = Enemy.class;
        Method useSkill = archerClassClass.getDeclaredMethod("useSkill", countItemArgs);

        assertTrue(Modifier.isPublic(useSkill.getModifiers()));
        assertEquals(2, useSkill.getParameterCount());

        when(mockAccount.getMaxMana()).thenReturn(100);
        Hero hero = new Hero(mockAccount);
        HeroClass archer = new ArcherClass();
        BossMonster bossMonster = new BossMonster(0);

        String usingSkill = archer.useSkill(hero, bossMonster);
        assertEquals("Hero's attack added by 20%", usingSkill);
        assertEquals(75, hero.getMana());

        hero.setMana(10);
        usingSkill = archer.useSkill(hero, bossMonster);
        assertEquals("Not enough mana", usingSkill);
        assertEquals(10, hero.getMana());

    }

    @Test
    void testArcherHeroHasGiveBonusFromEquipmentMethod () throws Exception{
        Class<?>[] countItemArgs = new Class[1];
        countItemArgs[0] = Hero.class;
        Method giveBonusFromEquipment = archerClassClass.getDeclaredMethod("giveBonusFromEquipment", countItemArgs);

        assertTrue(Modifier.isPublic(giveBonusFromEquipment.getModifiers()));
        assertEquals(1, giveBonusFromEquipment.getParameterCount());
        
        HeroClass archer = new ArcherClass();
        when(mockAccount.getArmorLevel()).thenReturn(1);
        when(mockAccount.getWeaponLevel()).thenReturn(1);

        Hero hero = new Hero(mockAccount);
        hero.setAttack(80);
        hero.setDefense(50);
        assertEquals(80, hero.getAttack());
        assertEquals(50, hero.getDefense());
        archer.giveBonusFromEquipment(hero);
        assertEquals(115, hero.getAttack());
        assertEquals(70, hero.getDefense());
    }
}
