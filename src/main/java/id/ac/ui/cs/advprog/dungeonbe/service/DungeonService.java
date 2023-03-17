package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.core.dungeon.Dungeon;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.DungeonDTO;

public interface DungeonService {

    DungeonDTO startDungeon(String id);
    Dungeon getDungeonFromId(String id);
    DungeonDTO attack(String id);
    DungeonDTO item(String id);
    DungeonDTO useItem(String id, int idx);
    DungeonDTO skill(String id);
    DungeonDTO leave(String id);
    DungeonDTO continues(String id);
    DungeonDTO timeout(String id);

}
