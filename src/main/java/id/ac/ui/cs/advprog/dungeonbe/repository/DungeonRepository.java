package id.ac.ui.cs.advprog.dungeonbe.repository;


import id.ac.ui.cs.advprog.dungeonbe.core.dungeon.Dungeon;
import org.springframework.stereotype.Repository;

@Repository
public interface DungeonRepository {
    Dungeon findByName(String id);
    void add(String id, Dungeon dungeon);
    void deleteById(String id);


}
