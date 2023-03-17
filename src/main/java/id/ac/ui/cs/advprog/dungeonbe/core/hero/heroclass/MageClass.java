package id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass;

import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;

public class MageClass implements HeroClass{
    @Override
    public String useSkill(Hero hero, Enemy enemy) {
        int mana = hero.getMana();
        if(mana-15 < 0) return "Not enough mana";
        else hero.setMana(mana-15);

        int attack = hero.getAttack();
        int skillAttack = (int) Math.round(attack * 1.1);

        hero.setAttack(skillAttack);
        hero.attack(enemy);
        hero.setAttack(attack);

        return "Attacking using skill";
    }

    @Override
    public void giveBonusFromEquipment(Hero hero){
        var armor = hero.getArmor();
        var weapon = hero.getWeapon();

        hero.setDefense(hero.getDefense()+armor.getDefense());
        hero.setAttack(hero.getAttack()+weapon.getAttack());
        hero.setMaxMana(hero.getMaxMana()+armor.getBonusAttribute());
        hero.setMana(hero.getMaxMana());
        hero.setAttack(hero.getAttack()+(weapon.getBonusAttribute()/2));
        hero.setDefense(hero.getDefense()+(weapon.getBonusAttribute()/2));
    }
}
