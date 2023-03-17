package id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass;

import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;

public class ArcherClass implements HeroClass{
    @Override
    public String useSkill(Hero hero, Enemy enemy) {
        int mana = hero.getMana();
        if(mana-25 < 0) return "Not enough mana";
        else hero.setMana(mana-25);

        int attack = hero.getAttack();
        int attackBonus = (int) Math.round(attack * 0.2);
        hero.setAttack(attack + attackBonus);

        return "Hero's attack added by 20%";
    }

    @Override
    public void giveBonusFromEquipment(Hero hero){
        var armor = hero.getArmor();
        var weapon = hero.getWeapon();

        hero.setDefense(hero.getDefense()+armor.getDefense());
        hero.setAttack(hero.getAttack()+weapon.getAttack());
        hero.setMaxHealth(hero.getMaxHealth()+(armor.getBonusAttribute()/2));
        hero.setHealth(hero.getMaxHealth());
        hero.setMaxMana(hero.getMaxMana()+(armor.getBonusAttribute()/2));
        hero.setMana(hero.getMaxMana());
        hero.setAttack(hero.getAttack()+weapon.getBonusAttribute());
    }
}
