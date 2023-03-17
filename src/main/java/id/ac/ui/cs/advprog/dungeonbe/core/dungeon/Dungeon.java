package id.ac.ui.cs.advprog.dungeonbe.core.dungeon;

import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.DungeonDTO;

public interface Dungeon {
    DungeonDTO attack();
    DungeonDTO useSkill();
    DungeonDTO useItem(int idx);
    DungeonDTO item();
    DungeonDTO heroContinue();
    DungeonDTO heroLeave();
    DungeonDTO heroTimeOut();
    Account getAccount();
    Enemy getEnemy();
}
