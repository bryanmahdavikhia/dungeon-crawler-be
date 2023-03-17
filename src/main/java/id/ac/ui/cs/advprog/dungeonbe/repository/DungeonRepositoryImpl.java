package id.ac.ui.cs.advprog.dungeonbe.repository;

import id.ac.ui.cs.advprog.dungeonbe.core.dungeon.Dungeon;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DungeonRepositoryImpl implements DungeonRepository{

    private Map<String, Dungeon> userDungeon = new HashMap<>();

    @Override
    public Dungeon findByName(String id) {
        return userDungeon.get(id);
    }

    @Override
    public void add(String id, Dungeon dungeon) {
        userDungeon.put(id, dungeon);
    }

    @Override
    public void deleteById(String id) {
        userDungeon.remove(id);
    }
}
