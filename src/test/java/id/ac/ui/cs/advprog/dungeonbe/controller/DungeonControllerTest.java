package id.ac.ui.cs.advprog.dungeonbe.controller;

import id.ac.ui.cs.advprog.dungeonbe.core.dungeon.DungeonImpl;
import id.ac.ui.cs.advprog.dungeonbe.core.dungeon.DungeonState;
import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Monster;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClassType;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.DungeonCommandDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.DungeonDTO;
import id.ac.ui.cs.advprog.dungeonbe.service.DungeonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DungeonController.class)
class DungeonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DungeonService dungeonService;

    private Hero sampleHero;
    private Account sampleAccount;
    private Enemy sampleEnemy;

    @BeforeEach
    void setUp(){
        sampleAccount = new Account();
        sampleAccount.setId("0");
        sampleAccount.setHeroClassType(HeroClassType.SWORDSMAN);
        sampleHero = new Hero(sampleAccount);
        sampleEnemy = new Monster(0);
    }

    @Test
    void testCheckDungeon() throws Exception{
        when(dungeonService.getDungeonFromId("0")).thenReturn(new DungeonImpl(sampleHero));

        DungeonCommandDTO req = new DungeonCommandDTO();
        req.setId("0");

        mockMvc.perform(post("/dungeon/check")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk());
    }

    @Test
    void testCheckNonExistDungeon() throws Exception{
        when(dungeonService.getDungeonFromId("0")).thenReturn(null);

        DungeonCommandDTO req = new DungeonCommandDTO();
        req.setId("0");

        mockMvc.perform(post("/dungeon/check")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testStartDungeon() throws Exception{
        when(dungeonService.startDungeon("0")).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.NORMAL, null));

        DungeonCommandDTO req = new DungeonCommandDTO();
        req.setId("0");
        req.setItemIndex(0);

        mockMvc.perform(post("/dungeon/start")
                .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dungeonState").value("NORMAL"));
    }

    @Test
    void testAttack() throws Exception{
        when(dungeonService.attack("0")).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.NORMAL, null));

        DungeonCommandDTO req = new DungeonCommandDTO();
        req.setId("0");
        req.setItemIndex(0);

        mockMvc.perform(post("/dungeon/attack")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dungeonState").value("NORMAL"));
    }

    @Test
    void testItem() throws Exception{
        when(dungeonService.item("0")).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.ITEM, null));

        DungeonCommandDTO req = new DungeonCommandDTO();
        req.setId("0");
        req.setItemIndex(0);

        mockMvc.perform(post("/dungeon/item")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dungeonState").value("ITEM"));
    }

    @Test
    void testUseItem() throws Exception{
        when(dungeonService.useItem("0", 0)).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.NORMAL, null));

        DungeonCommandDTO req = new DungeonCommandDTO();
        req.setId("0");
        req.setItemIndex(0);

        mockMvc.perform(post("/dungeon/useitem")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dungeonState").value("NORMAL"));
    }

    @Test
    void testSkill() throws Exception{
        when(dungeonService.skill("0")).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.NORMAL, null));

        DungeonCommandDTO req = new DungeonCommandDTO();
        req.setId("0");
        req.setItemIndex(0);

        mockMvc.perform(post("/dungeon/skill")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dungeonState").value("NORMAL"));
    }

    @Test
    void testLeave() throws Exception{
        when(dungeonService.leave("0")).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.LEAVE, null));

        DungeonCommandDTO req = new DungeonCommandDTO();
        req.setId("0");
        req.setItemIndex(0);

        mockMvc.perform(post("/dungeon/leave")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dungeonState").value("LEAVE"));
    }

    @Test
    void testContinue() throws Exception{
        when(dungeonService.continues("0")).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.NORMAL, null));

        DungeonCommandDTO req = new DungeonCommandDTO();
        req.setId("0");
        req.setItemIndex(0);

        mockMvc.perform(post("/dungeon/continue")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dungeonState").value("NORMAL"));
    }

    @Test
    void testTimeout() throws Exception{
        when(dungeonService.timeout("0")).thenReturn(new DungeonDTO(sampleHero, sampleEnemy, DungeonState.TIMEOUT, null));

        DungeonCommandDTO req = new DungeonCommandDTO();
        req.setId("0");
        req.setItemIndex(0);

        mockMvc.perform(post("/dungeon/timeout")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dungeonState").value("TIMEOUT"));
    }
}
