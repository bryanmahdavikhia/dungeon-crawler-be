package id.ac.ui.cs.advprog.dungeonbe.controller;

import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.TownDTO;
import id.ac.ui.cs.advprog.dungeonbe.service.TownService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TownController.class)
class TownControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TownService townService;

    private Account sampleAccount;
    private Account sampleAccount2;
    private List<Account> accounts;

    @BeforeEach
    void setUp(){
        sampleAccount = new Account();
        sampleAccount2 = new Account();
        sampleAccount.setDungeonLevel(2);
        sampleAccount2.setDungeonLevel(1);
        accounts = new ArrayList<>();
        accounts.add(sampleAccount);
        accounts.add(sampleAccount2);

    }

    @Test
    void testSeeLeaderboard() throws Exception{
        TownDTO ret = new TownDTO();
        ret.setLeaderboard(accounts);
        when(townService.setLeaderboard()).thenReturn(ret);


        mockMvc.perform(post("/town/leaderboard/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.leaderboard[0].dungeonLevel").value(2))
                .andExpect(jsonPath("$.leaderboard[1].dungeonLevel").value(1));
    }
}
