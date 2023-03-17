package id.ac.ui.cs.advprog.dungeonbe.core.enemy;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Monster extends Enemy {

    public Monster(int dungeonLevel) {
        super(120, 20, 10, 10, dungeonLevel);
    }

    @Override
    public List<String> attack(Hero hero) {
        List<String> logs =  new ArrayList<>();
        logs.add(this + " is attacking");
        String log = hero.defend(attackStat);
        logs.add(log);
        return logs;
    }

    @Override
    public String toString() {
        return "Monster";
    }
}
