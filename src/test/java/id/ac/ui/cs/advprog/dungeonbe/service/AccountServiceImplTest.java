package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClassType;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account sampleAccount;

    @BeforeEach
    void setUp(){
        sampleAccount = new Account();
        sampleAccount.setId("0");
        sampleAccount.setHeroClassType(HeroClassType.SWORDSMAN);
    }

    @Test
    void testGetAccountByIdMethod(){
        when(accountRepository.findAccountById("0")).thenReturn(sampleAccount);

        Account account = accountService.getAccountById("0");
        assertEquals("0", account.getId());
        assertEquals(HeroClassType.SWORDSMAN, account.getHeroClassType());
    }

    @Test
    void testCreateAccountMethod(){
        Account account = accountService.createAccount("0", HeroClassType.SWORDSMAN);
        verify(accountRepository).save(account);
    }

    @Test
    void testAccountExistByIdMethod(){
        when(accountRepository.existsById("0")).thenReturn(true);
        assertTrue(accountService.accountExistsById("0"));
    }

    @Test
    void testSaveAccount(){
        accountService.saveAccount(sampleAccount);
        verify(accountRepository).save(sampleAccount);
    }
}
