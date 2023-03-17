package id.ac.ui.cs.advprog.dungeonbe.core.dungeon;

import id.ac.ui.cs.advprog.dungeonbe.core.enemy.BossMonster;
import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
import id.ac.ui.cs.advprog.dungeonbe.core.enemy.MonsterFactory;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.DungeonDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DungeonImpl implements Dungeon {

    private final Hero hero;
    private Enemy enemy;
    private int enemyDefeated;
    private MonsterFactory monsterFactory;
    private DungeonState dungeonState;
    private static final int ENEMY_NEEDED_BEFORE_BOSS = 9;
    private static final double REDUCED_RATIO = 0.9;

    public DungeonImpl(Hero hero) {
        this.hero = hero;
        this.enemyDefeated = 0;
        this.monsterFactory = new MonsterFactory(ENEMY_NEEDED_BEFORE_BOSS);
        this.enemy = monsterFactory.generateEnemy(enemyDefeated, hero.getDungeonLevel());
        this.dungeonState = DungeonState.NORMAL;
    }

    @Override
    public DungeonDTO attack() {
        List<String> heroLog = hero.attack(enemy);
        return afterHeroTurn(heroLog);
    }

    @Override
    public DungeonDTO useSkill() {
        List<String> heroLog = hero.useSkill(enemy);
        return afterHeroTurn(heroLog);
    }

    @Override
    public DungeonDTO useItem(int idx) {
        List<String> heroLog = hero.useItem(idx);
        return afterHeroTurn(heroLog);
    }

    @Override
    public DungeonDTO item() {
        return new DungeonDTO(hero, enemy, DungeonState.ITEM, hero.getListNameItems());
    }

    @Override
    public DungeonDTO heroContinue() {
        List<String> logs = new ArrayList<>();
        enemy = monsterFactory.generateEnemy(enemyDefeated, hero.getDungeonLevel());
        dungeonState = DungeonState.NORMAL;
        logs.add("An enemy " + enemy + " appeared!");
        return new DungeonDTO(hero, enemy, dungeonState, logs);
    }

    @Override
    public DungeonDTO heroLeave() {
        List<String> logs = new ArrayList<>();
        dungeonState = DungeonState.LEAVE;
        logs.add("Hero leave the " + this);
        applyHeroReward(logs);
        return new DungeonDTO(hero, enemy, dungeonState, logs);
    }

    @Override
    public DungeonDTO heroTimeOut() {
        List<String> logs = new ArrayList<>();
        dungeonState = DungeonState.TIMEOUT;
        logs.add("Hero took too long to respond");
        reduceReward(logs);
        applyHeroReward(logs);
        return new DungeonDTO(hero, enemy, dungeonState, logs);
    }

    @Override
    public String toString() {
        return "Dungeon";
    }

    @Override
    public Account getAccount() {
        return hero.getAccount();
    }

    @Override
    public Enemy getEnemy(){
        return this.enemy;
    }

    private DungeonDTO afterHeroTurn(List<String> heroLog) {
        List<String> logs = new ArrayList<>(heroLog);

        enemyTurn(logs);
        return new DungeonDTO(hero, enemy, dungeonState, logs);
    }

    private void enemyTurn(List<String> logs) {
        if (enemy.isAlive()) {
            enemyAttack(logs);
        }else {
            enemyDefeat(logs);
        }

        if (!hero.isAlive()) {
            onHeroDead(logs);
        }
    }

    private void enemyDefeat(List<String> logs) {
        logs.add(enemy + " has been defeated");
        enemyDefeated += 1;
        addReward();

        if (enemy instanceof BossMonster) {
            onHeroFinish(logs);
        } else {
            onHeroWin();
        }
    }

    private void onHeroFinish(List<String> logs) {
        dungeonState = DungeonState.FINISH;
        hero.setDungeonLevel(hero.getDungeonLevel()+1);
        logs.add(this + " has been finished");
        applyHeroReward(logs);
    }

    private void onHeroWin() {
        dungeonState = DungeonState.WIN;
    }

    private void onHeroDead(List<String> logs) {
        dungeonState = DungeonState.DEATH;
        logs.add("Hero fainted");
        reduceReward(logs);
        applyHeroReward(logs);
    }

    private void applyHeroReward(List<String> logs) {
        List<String> logReward = hero.destructor();
        logs.addAll(logReward);
    }

    private void enemyAttack(List<String> logs) {
        List<String> enemyLog = enemy.attack(hero);
        logs.addAll(enemyLog);
    }

    private void addReward() {
        hero.setExpCollected(hero.getExpCollected()+enemy.getExp());
        hero.setGoldCollected(hero.getGoldCollected()+enemy.getGold());
    }

    private void reduceReward(List<String> logs) {
        hero.setExpCollected((int) (hero.getExpCollected()* REDUCED_RATIO));
        hero.setGoldCollected((int) (hero.getGoldCollected()* REDUCED_RATIO));
        logs.add("Reward has been reduced");
    }
}
