package id.ac.ui.cs.advprog.dungeonbe.repository;

import id.ac.ui.cs.advprog.dungeonbe.core.dungeon.Dungeon;
import id.ac.ui.cs.advprog.dungeonbe.core.dungeon.DungeonImpl;
import id.ac.ui.cs.advprog.dungeonbe.core.dungeon.DungeonState;
import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Monster;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClassType;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.DungeonDTO;
import id.ac.ui.cs.advprog.dungeonbe.repository.AccountRepository;
import id.ac.ui.cs.advprog.dungeonbe.repository.DungeonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DungeonRepositoryImplTest {

    @InjectMocks
    private DungeonRepositoryImpl dungeonRepository;

    private Hero sampleHero;
    private Account sampleAccount;
    private Dungeon sampleDungeon;

    @BeforeEach
    void setUp(){
        sampleAccount = new Account();
        sampleAccount.setId("0");
        sampleAccount.setHeroClassType(HeroClassType.SWORDSMAN);
        sampleHero = new Hero(sampleAccount);
        sampleDungeon = new DungeonImpl(sampleHero);
    }

    @Test
    void testFindByName(){
        dungeonRepository.add("0", sampleDungeon);
        Dungeon dungeon = dungeonRepository.findByName("0");
        assertEquals(sampleDungeon, dungeon);
    }

    @Test
    void testAdd(){
        dungeonRepository.add("0", sampleDungeon);
        Dungeon dungeon = dungeonRepository.findByName("0");
        assertEquals(sampleDungeon, dungeon);
    }

    @Test
    void testDeleteById(){
        dungeonRepository.add("0", sampleDungeon);
        Dungeon dungeon = dungeonRepository.findByName("0");
        assertEquals(sampleDungeon, dungeon);

        dungeonRepository.deleteById("0");
        dungeon = dungeonRepository.findByName("0");
        assertEquals(null, dungeon);
    }

}
