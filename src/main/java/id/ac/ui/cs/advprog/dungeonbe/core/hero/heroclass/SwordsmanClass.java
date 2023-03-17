package id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass;

import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;

public class SwordsmanClass implements HeroClass{
    @Override
    public String useSkill(Hero hero, Enemy enemy) {
        int mana = hero.getMana();
        if(mana-20 < 0) return "Not enough mana";
        else hero.setMana(mana-20);

        int defense = hero.getDefense();
        int defenseBonus = (int) Math.round(defense * 0.3);
        hero.setDefense(defense + defenseBonus);

        return "Hero's defense added by 30%";
    }

    @Override
    public void giveBonusFromEquipment(Hero hero){
        var armor = hero.getArmor();
        var weapon = hero.getWeapon();

        hero.setDefense(hero.getDefense()+armor.getDefense());
        hero.setAttack(hero.getAttack()+weapon.getAttack());
        hero.setMaxHealth(hero.getMaxHealth()+armor.getBonusAttribute());
        hero.setHealth(hero.getMaxHealth());
        hero.setDefense(hero.getDefense()+weapon.getBonusAttribute());
    }
}
