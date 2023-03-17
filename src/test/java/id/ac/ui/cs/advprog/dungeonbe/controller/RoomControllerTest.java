package id.ac.ui.cs.advprog.dungeonbe.controller;

import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.RoomDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.UpgradeStatsDTO;
import id.ac.ui.cs.advprog.dungeonbe.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RoomController.class)
class RoomControllerTest {
    private Class<?> accountClass;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Mock
    Account mockAccount;

    @BeforeEach
    void setUp() throws Exception{
        accountClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.model.Account");
        mockAccount = (Account) mock(accountClass);
    }

    @Test
    void testUpgradeAttack() throws Exception{
        String mockAccountId = mockAccount.getId();
        UpgradeStatsDTO upgradeStatsDTO = new UpgradeStatsDTO();

        upgradeStatsDTO.setAdded(true);
        when(roomService.upgradeAttack(mockAccountId)).thenReturn(upgradeStatsDTO);

        RoomDTO req = new RoomDTO();
        req.setId(mockAccountId);

        mockMvc.perform(post("/room/upgrade-attack")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.added").value(true));
    }

    @Test
    void testUpgradeDefense() throws Exception{
        String mockAccountId = mockAccount.getId();
        UpgradeStatsDTO upgradeStatsDTO = new UpgradeStatsDTO();

        upgradeStatsDTO.setAdded(true);
        when(roomService.upgradeDefense(mockAccountId)).thenReturn(upgradeStatsDTO);

        RoomDTO req = new RoomDTO();
        req.setId(mockAccountId);

        mockMvc.perform(post("/room/upgrade-defense")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.added").value(true));
    }

    @Test
    void testUpgradeMana() throws Exception{
        String mockAccountId = mockAccount.getId();
        UpgradeStatsDTO upgradeStatsDTO = new UpgradeStatsDTO();

        upgradeStatsDTO.setAdded(true);
        when(roomService.upgradeMana(mockAccountId)).thenReturn(upgradeStatsDTO);

        RoomDTO req = new RoomDTO();
        req.setId(mockAccountId);

        mockMvc.perform(post("/room/upgrade-mana")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.added").value(true));
    }

}
