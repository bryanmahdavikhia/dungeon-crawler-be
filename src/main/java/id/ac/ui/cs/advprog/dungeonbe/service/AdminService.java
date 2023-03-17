package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClassType;

public interface AdminService {

    boolean setHealth(String id, int health);
    boolean setMana(String id, int mana);
    boolean setAttack(String id, int attack);
    boolean setDefense(String id, int defense);
    boolean setGold(String id, int gold);
    boolean setWeaponLevel(String id, int weaponLevel);
    boolean setArmorLevel(String id, int armorLevel);
    boolean setHeroClassType(String id, HeroClassType heroClassType);
}
