package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.TownDTO;
import id.ac.ui.cs.advprog.dungeonbe.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TownServiceImplTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TownServiceImpl townService;

    private Account sampleAccount;
    private Account sampleAccount2;
    private List<Account> accounts;
    private int length;
    private TownDTO townDTO;

    @BeforeEach
    void setUp(){
        sampleAccount = new Account();
        sampleAccount2 = new Account();
        sampleAccount.setDungeonLevel(2);
        sampleAccount2.setDungeonLevel(1);

        accounts = List.of(sampleAccount, sampleAccount2);
        length = accounts.size();

    }

    @Test
    void testSetLeaderboard(){
        when(accountRepository.findAll(Sort.by(Sort.Direction.DESC, "DungeonLevel"))).thenReturn(accounts);

        townDTO = townService.setLeaderboard();
        assertEquals(accounts, townDTO.getLeaderboard());
        assertEquals(true,accounts.size()<10);
        assertEquals(2, length);
        assertEquals(2, accounts.size());

        Account sampleAccount3 = new Account();
        Account sampleAccount4 = new Account();
        Account sampleAccount5 = new Account();
        Account sampleAccount6 = new Account();
        Account sampleAccount7 = new Account();
        Account sampleAccount8 = new Account();
        Account sampleAccount9 = new Account();
        Account sampleAccount10 = new Account();

        sampleAccount3.setDungeonLevel(1);
        sampleAccount4.setDungeonLevel(1);
        sampleAccount5.setDungeonLevel(1);
        sampleAccount6.setDungeonLevel(1);
        sampleAccount7.setDungeonLevel(1);
        sampleAccount8.setDungeonLevel(1);
        sampleAccount9.setDungeonLevel(1);
        sampleAccount10.setDungeonLevel(1);
        accounts = List.of(sampleAccount, sampleAccount2, sampleAccount3, sampleAccount4, sampleAccount5, sampleAccount6, sampleAccount7, sampleAccount8, sampleAccount9, sampleAccount10);
        assertEquals(false,accounts.size()<10);
        when(accountRepository.findAll(Sort.by(Sort.Direction.DESC, "DungeonLevel"))).thenReturn(accounts);
        townDTO = townService.setLeaderboard();
        assertEquals(accounts, townDTO.getLeaderboard());
    }
}
