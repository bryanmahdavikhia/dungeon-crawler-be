package id.ac.ui.cs.advprog.dungeonbe.core.enemy;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public abstract class Enemy{
    protected int healthStat;
    protected int attackStat;
    protected int exp;
    protected int gold;

    protected Enemy(int health, int attack, int exp, int gold, int dungeonLevel){
        this.healthStat = health * (dungeonLevel+1);
        this.attackStat = attack * (dungeonLevel+1);
        this.exp = exp * (dungeonLevel+1);
        this.gold = gold * (dungeonLevel+1);
    }

    public String defend(int damage) {
        setHealthStat(healthStat-damage);
        return this + " Current Health: " + healthStat;
    }

    public abstract List<String> attack(Hero hero);

    public void setHealthStat(int healthStat) {
        this.healthStat = Math.max(0, healthStat);
    }

    public boolean isAlive() {
        return getHealthStat() > 0;
    }
}
