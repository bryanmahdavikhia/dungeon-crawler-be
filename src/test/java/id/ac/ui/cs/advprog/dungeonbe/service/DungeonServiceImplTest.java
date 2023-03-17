package id.ac.ui.cs.advprog.dungeonbe.service;

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
class DungeonServiceImplTest {

    @Mock
    private DungeonRepository dungeonRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private HeroService heroService;

    @Mock
    private Dungeon dungeonMock;

    @InjectMocks
    private DungeonServiceImpl dungeonService;

    private Hero sampleHero;
    private Account sampleAccount;
    private Dungeon sampleDungeon;
    private Enemy sampleEnemy;

    @BeforeEach
    void setUp(){
        sampleAccount = new Account();
        sampleAccount.setId("0");
        sampleAccount.setHeroClassType(HeroClassType.SWORDSMAN);
        sampleHero = new Hero(sampleAccount);
        sampleDungeon = new DungeonImpl(sampleHero);
        sampleEnemy = new Monster(0);
    }

    @Test
    void testStartDungeon(){
        when(heroService.getHeroFromAccountData("0")).thenReturn(sampleHero);

        DungeonDTO resp = dungeonService.startDungeon("0");
        assertEquals(DungeonState.NORMAL, resp.getDungeonState());
        verify(dungeonRepository).add("0", sampleDungeon);
    }

    @Test
    void testGetDungeonFromId(){
        when(dungeonRepository.findByName("0")).thenReturn(sampleDungeon);

        Dungeon resp = dungeonService.getDungeonFromId("0");
        assertEquals(sampleDungeon, resp);
    }

    @Test
    void testAttackThenNormal(){
        when(dungeonRepository.findByName("0")).thenReturn(dungeonMock);
        when(dungeonMock.attack()).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.NORMAL, null));

        DungeonDTO resp = dungeonService.attack("0");
        assertEquals(DungeonState.NORMAL, resp.getDungeonState());
    }

    @Test
    void testAttackThenDeath(){
        when(dungeonRepository.findByName("0")).thenReturn(dungeonMock);
        when(dungeonMock.attack()).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.DEATH, null));
        when(dungeonMock.getAccount()).thenReturn(sampleAccount);

        DungeonDTO resp = dungeonService.attack("0");
        assertEquals(DungeonState.DEATH, resp.getDungeonState());
        verify(accountRepository).save(sampleAccount);
        verify(dungeonRepository).deleteById("0");
    }

    @Test
    void testAttackThenFinish(){
        when(dungeonRepository.findByName("0")).thenReturn(dungeonMock);
        when(dungeonMock.attack()).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.FINISH, null));
        when(dungeonMock.getAccount()).thenReturn(sampleAccount);

        DungeonDTO resp = dungeonService.attack("0");
        assertEquals(DungeonState.FINISH, resp.getDungeonState());
        verify(accountRepository).save(sampleAccount);
        verify(dungeonRepository).deleteById("0");
    }

    @Test
    void testItem(){
        when(dungeonRepository.findByName("0")).thenReturn(dungeonMock);
        when(dungeonMock.item()).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.ITEM, null));

        DungeonDTO resp = dungeonService.item("0");
        assertEquals(DungeonState.ITEM, resp.getDungeonState());
    }

    @Test
    void testUseItemThenNormal(){
        when(dungeonRepository.findByName("0")).thenReturn(dungeonMock);
        when(dungeonMock.useItem(0)).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.NORMAL, null));

        DungeonDTO resp = dungeonService.useItem("0", 0);
        assertEquals(DungeonState.NORMAL, resp.getDungeonState());
    }

    @Test
    void testUseItemThenDeath(){
        when(dungeonRepository.findByName("0")).thenReturn(dungeonMock);
        when(dungeonMock.useItem(0)).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.DEATH, null));
        when(dungeonMock.getAccount()).thenReturn(sampleAccount);

        DungeonDTO resp = dungeonService.useItem("0", 0);
        assertEquals(DungeonState.DEATH, resp.getDungeonState());
        verify(accountRepository).save(sampleAccount);
        verify(dungeonRepository).deleteById("0");
    }

    @Test
    void testUseItemThenFinish(){
        when(dungeonRepository.findByName("0")).thenReturn(dungeonMock);
        when(dungeonMock.useItem(0)).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.FINISH, null));
        when(dungeonMock.getAccount()).thenReturn(sampleAccount);

        DungeonDTO resp = dungeonService.useItem("0", 0);
        assertEquals(DungeonState.FINISH, resp.getDungeonState());
        verify(accountRepository).save(sampleAccount);
        verify(dungeonRepository).deleteById("0");
    }

    @Test
    void testSkillThenNormal(){
        when(dungeonRepository.findByName("0")).thenReturn(dungeonMock);
        when(dungeonMock.useSkill()).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.NORMAL, null));

        DungeonDTO resp = dungeonService.skill("0");
        assertEquals(DungeonState.NORMAL, resp.getDungeonState());
    }

    @Test
    void testSkillThenDeath(){
        when(dungeonRepository.findByName("0")).thenReturn(dungeonMock);
        when(dungeonMock.useSkill()).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.DEATH, null));
        when(dungeonMock.getAccount()).thenReturn(sampleAccount);

        DungeonDTO resp = dungeonService.skill("0");
        assertEquals(DungeonState.DEATH, resp.getDungeonState());
        verify(accountRepository).save(sampleAccount);
        verify(dungeonRepository).deleteById("0");
    }

    @Test
    void testSkillThenFinish(){
        when(dungeonRepository.findByName("0")).thenReturn(dungeonMock);
        when(dungeonMock.useSkill()).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.FINISH, null));
        when(dungeonMock.getAccount()).thenReturn(sampleAccount);

        DungeonDTO resp = dungeonService.skill("0");
        assertEquals(DungeonState.FINISH, resp.getDungeonState());
        verify(accountRepository).save(sampleAccount);
        verify(dungeonRepository).deleteById("0");
    }

    @Test
    void testLeave(){
        when(dungeonRepository.findByName("0")).thenReturn(dungeonMock);
        when(dungeonMock.heroLeave()).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.LEAVE, null));
        when(dungeonMock.getAccount()).thenReturn(sampleAccount);

        DungeonDTO resp = dungeonService.leave("0");
        assertEquals(DungeonState.LEAVE, resp.getDungeonState());
        verify(accountRepository).save(sampleAccount);
        verify(dungeonRepository).deleteById("0");
    }

    @Test
    void testContinue(){
        when(dungeonRepository.findByName("0")).thenReturn(dungeonMock);
        when(dungeonMock.heroContinue()).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.NORMAL, null));

        DungeonDTO resp = dungeonService.continues("0");
        assertEquals(DungeonState.NORMAL, resp.getDungeonState());
    }

    @Test
    void testTimeout(){
        when(dungeonRepository.findByName("0")).thenReturn(dungeonMock);
        when(dungeonMock.heroTimeOut()).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.TIMEOUT, null));
        when(dungeonMock.getAccount()).thenReturn(sampleAccount);

        DungeonDTO resp = dungeonService.timeout("0");
        assertEquals(DungeonState.TIMEOUT, resp.getDungeonState());
        verify(accountRepository).save(sampleAccount);
        verify(dungeonRepository).deleteById("0");
    }
}
