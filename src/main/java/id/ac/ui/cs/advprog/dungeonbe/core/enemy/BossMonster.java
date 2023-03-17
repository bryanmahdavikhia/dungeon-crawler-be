package id.ac.ui.cs.advprog.dungeonbe.core.enemy;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Random;

@EqualsAndHashCode
public class BossMonster extends Enemy{
    private final PrimitiveIterator.OfInt rngSpecialAttackIter;

    public BossMonster(int dungeonLevel) {
        super(250, 30, 25, 30, dungeonLevel);
        var rng = new Random();
        rngSpecialAttackIter = rng.ints(1, 100).iterator();
    }

    @Override
    public List<String> attack(Hero hero) {
        List<String> logs =  new ArrayList<>();
        logs.add(this + " is attacking");

        String defendLog;
        if (rngSpecialAttack() <= 20) {
            defendLog = specialAttack(hero);
        }else{
            defendLog = normalAttack(hero);
        }
        logs.add(defendLog);
        return logs;
    }

    @Override
    public String toString() {
        return "Boss";
    }

    protected int rngSpecialAttack() {
        return rngSpecialAttackIter.nextInt();
    }

    protected String normalAttack(Hero hero) {
        hero.defend(attackStat);
        return this + " doing normal attack";
    }

    protected String specialAttack(Hero hero) {
        hero.defend(attackStat*2);
        return this + " doing special attack";
    }
}
