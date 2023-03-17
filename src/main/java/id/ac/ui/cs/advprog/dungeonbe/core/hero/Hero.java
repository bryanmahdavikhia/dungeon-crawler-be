package id.ac.ui.cs.advprog.dungeonbe.core.hero;

import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
import id.ac.ui.cs.advprog.dungeonbe.core.equipment.Armor;
import id.ac.ui.cs.advprog.dungeonbe.core.equipment.ArmorEquipment;
import id.ac.ui.cs.advprog.dungeonbe.core.equipment.Weapon;
import id.ac.ui.cs.advprog.dungeonbe.core.equipment.WeaponEquipment;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.*;
import id.ac.ui.cs.advprog.dungeonbe.core.item.Item;
import id.ac.ui.cs.advprog.dungeonbe.core.item.ItemFactory;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Hero {

    private int health;
    private int maxHealth;
    private int mana;
    private int maxMana;
    private int attack;
    private int defense;
    private int expCollected;
    private int goldCollected;
    private int level;
    private int dungeonLevel;
    private HeroClass heroClass;
    private Account account;
    private List<Item> items;
    private Weapon weapon;
    private Armor armor;

    public Hero(Account account){
        // These are temporary values
        // Will change stats initialization once all core entity have correctly implemented
        this.health = account.getMaxHealth();
        this.maxHealth = account.getMaxHealth();
        this.mana = account.getMaxMana();
        this.maxMana = account.getMaxMana();
        this.attack = account.getAttack();
        this.defense = account.getDefense();
        this.expCollected = 0;
        this.goldCollected = 0;
        this.level = account.getLevel();
        this.dungeonLevel = account.getDungeonLevel();
        this.heroClass = getHeroClassFromEnum(account.getHeroClassType());
        this.account = account;
        this.items = getItemsFromItemString(account.getItems());
        this.weapon = new WeaponEquipment(account.getWeaponLevel());
        this.armor = new ArmorEquipment(account.getArmorLevel());
        this.heroClass.giveBonusFromEquipment(this);
    }

    private HeroClass getHeroClassFromEnum(HeroClassType heroClassType){
        if (heroClassType == HeroClassType.SWORDSMAN)
            return new SwordsmanClass();
        else if (heroClassType == HeroClassType.ARCHER)
            return new ArcherClass();
        else
            return new MageClass();
    }

    private List<Item> getItemsFromItemString(String items){
        var itemFactory = new ItemFactory();
        List<String> stringItemsId;
        List<Item> itemList = new ArrayList<>();

        if(items == null) return itemList;
        if(items.equals("")) return itemList;
        stringItemsId = Arrays.asList(items.split(",", -1));
        for(String id : stringItemsId){
            itemList.add(itemFactory.createItem(Integer.parseInt(id), dungeonLevel));
        }

        return itemList;
    }

    public List<String> attack(Enemy enemy){
        List<String> logs = new ArrayList<>();
        logs.add("Attacking " + enemy);
        String log = enemy.defend(attack);
        logs.add(log);
        return logs;
    }

    public String defend(int damage){
        int damageDealt = Math.max(0, damage-defense);

        setHealth(health - damageDealt);
        return "Hero's damage received: " + damageDealt;
    }

    public List<String> useSkill(Enemy enemy){
        List<String> logs = new ArrayList<>();
        logs.add("Using skill");

        String skillLog = heroClass.useSkill(this, enemy);
        logs.add(skillLog);
        return logs;
    }

    public List<String> useItem(int idx){
        List<String> logs = new ArrayList<>();

        idx -= 1;
        var item = items.remove(idx);
        logs.add("Using " + item);
        String itemLog = item.useItem(this);
        logs.add(itemLog);
        return logs;
    }

    public List<String> destructor() {
        List<String> logs = new ArrayList<>();
        String rewardLog = saveReward();
        logs.add(rewardLog);

        List<String> levelUpLog = checkLevelUp();
        logs.addAll(levelUpLog);
        return logs;
    }

    private List<String> checkLevelUp() {
        int currentExp = account.getExp();
        List<String> logs = new ArrayList<>();

        while (currentExp >= expNeeded()) {
            currentExp -= expNeeded();
            handleLevelUp();
            level+=1;
            logs.add("Hero leveled up");
        }
        account.setLevel(level);
        account.setExp(currentExp);
        return logs;
    }

    private void handleLevelUp() {
        int currMaxHealth = account.getMaxHealth();
        int stockAttack = account.getAttack();
        int stockDefense = account.getDefense();

        account.setMaxHealth(currMaxHealth+100);
        account.setAttack(stockAttack+50);
        account.setDefense(stockDefense+30);
    }

    private String saveReward() {
        List<String> listIdItems = getListIdItems().stream().map(Object::toString).collect(Collectors.toList());
        account.setItems(String.join(",", listIdItems));
        account.setExp(account.getExp() + expCollected);
        account.setGold(account.getGold() + goldCollected);
        account.setDungeonLevel(dungeonLevel);
        String log = "Hero get " + expCollected + " exp and " + goldCollected + " gold";
        expCollected = 0;
        goldCollected = 0;
        return log;
    }

    public int expNeeded() {
        return level * 100;
    }

    public List<Integer> getListIdItems() {
        return items.stream().map(Item::getId).collect(Collectors.toList());
    }

    public List<String> getListNameItems() {
        return items.stream().map(Item::toString).collect(Collectors.toList());
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, health);
        this.health = Math.min(account.getMaxHealth(), this.health);
    }

    public void setMana(int mana) {
        this.mana = Math.max(0, mana);
        this.mana = Math.min(account.getMaxMana(), this.mana);
    }
}
