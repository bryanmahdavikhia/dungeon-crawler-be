package id.ac.ui.cs.advprog.dungeonbe.controller;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClassType;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.AccessAccountDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.CreateAccountDTO;
import id.ac.ui.cs.advprog.dungeonbe.service.AccountService;
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

@WebMvcTest(controllers = AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private Account account;
    private AccessAccountDTO accessAccountDTO;
    private CreateAccountDTO createAccountDTO;

    @BeforeEach
    void setUp(){
        account = new Account();
        account.setId("0");
        account.setHeroClassType(HeroClassType.SWORDSMAN);
        account.setMaxHealth(100);
        account.setMaxMana(100);
        account.setAttack(10);
        account.setDefense(10);
        account.setExp(0);
        account.setGold(0);
        account.setLevel(1);
        account.setDungeonLevel(0);
        account.setWeaponLevel(0);
        account.setArmorLevel(0);
        account.setItems("");

        accessAccountDTO = new AccessAccountDTO();
        accessAccountDTO.setId("0");

        createAccountDTO = new CreateAccountDTO();
        createAccountDTO.setId("0");
        createAccountDTO.setHeroClassType(HeroClassType.SWORDSMAN);
    }

    @Test
    void testGetAccountById() throws Exception{
        when(accountService.getAccountById("0")).thenReturn(account);
        when(accountService.accountExistsById("0")).thenReturn(true);

        mockMvc.perform(post("/account/")
                .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(accessAccountDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("0"))
                .andExpect(jsonPath("$.heroClassType").value("SWORDSMAN"));
    }

    @Test
    void testGetNonExistentAccountById() throws Exception{
        when(accountService.accountExistsById("0")).thenReturn(false);

        mockMvc.perform(post("/account/")
                .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(accessAccountDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateAccount() throws Exception{
        when(accountService.createAccount("0", HeroClassType.SWORDSMAN)).thenReturn(account);
        when(accountService.accountExistsById("0")).thenReturn(false);

        mockMvc.perform(post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(createAccountDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("0"))
                .andExpect(jsonPath("$.heroClassType").value("SWORDSMAN"));
    }

    @Test
    void testCreateAlreadyExistAccount() throws Exception{
        when(accountService.accountExistsById("0")).thenReturn(true);

        mockMvc.perform(post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(createAccountDTO)))
                .andExpect(status().isBadRequest());
    }
}
