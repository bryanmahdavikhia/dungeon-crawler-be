package id.ac.ui.cs.advprog.dungeonbe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClassType;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.AdminCommandDTO;
import id.ac.ui.cs.advprog.dungeonbe.service.AdminService;
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

@WebMvcTest(controllers = AdminController.class)
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @Test
    void testSetHealth() throws Exception {
        when(adminService.setHealth("0", 10)).thenReturn(true);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("0");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/health")
                .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk());
    }

    @Test
    void testSetHealthWhenFailed() throws Exception {
        when(adminService.setHealth("1", 10)).thenReturn(false);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("1");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/health")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSetMana() throws Exception {
        when(adminService.setMana("0", 10)).thenReturn(true);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("0");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/mana")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk());
    }

    @Test
    void testSetManaWhenFailed() throws Exception {
        when(adminService.setMana("1", 10)).thenReturn(false);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("1");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/mana")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSetAttack() throws Exception {
        when(adminService.setAttack("0", 10)).thenReturn(true);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("0");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/attack")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk());
    }

    @Test
    void testSetAttackWhenFailed() throws Exception {
        when(adminService.setAttack("1", 10)).thenReturn(false);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("1");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/attack")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSetDefense() throws Exception {
        when(adminService.setDefense("0", 10)).thenReturn(true);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("0");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/defense")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk());
    }

    @Test
    void testSetDefenseWhenFailed() throws Exception {
        when(adminService.setDefense("1", 10)).thenReturn(false);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("1");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/defense")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSetGold() throws Exception {
        when(adminService.setGold("0", 10)).thenReturn(true);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("0");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/gold")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk());
    }

    @Test
    void testSetGoldWhenFailed() throws Exception {
        when(adminService.setGold("1", 10)).thenReturn(false);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("1");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/gold")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSetWeaponLevel() throws Exception {
        when(adminService.setWeaponLevel("0", 10)).thenReturn(true);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("0");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/weaponlevel")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk());
    }

    @Test
    void testSetWeaponLevelWhenFailed() throws Exception {
        when(adminService.setWeaponLevel("1", 10)).thenReturn(false);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("1");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/weaponlevel")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSetArmorLevel() throws Exception {
        when(adminService.setArmorLevel("0", 10)).thenReturn(true);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("0");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/armorlevel")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk());
    }

    @Test
    void testSetArmorLevelWhenFailed() throws Exception {
        when(adminService.setArmorLevel("1", 10)).thenReturn(false);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("1");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/armorlevel")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSetHeroClassType() throws Exception {
        when(adminService.setHeroClassType("0", HeroClassType.MAGE)).thenReturn(true);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("0");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/heroclasstype")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk());
    }

    @Test
    void testSetHeroClassTypeWhenFailed() throws Exception {
        when(adminService.setHeroClassType("1", HeroClassType.MAGE)).thenReturn(false);

        AdminCommandDTO req = new AdminCommandDTO();
        req.setId("1");
        req.setValue(10);
        req.setHeroClassType(HeroClassType.MAGE);

        mockMvc.perform(post("/admin/heroclasstype")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isBadRequest());
    }
}
