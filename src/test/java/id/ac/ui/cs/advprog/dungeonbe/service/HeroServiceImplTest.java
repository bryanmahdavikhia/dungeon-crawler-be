package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClassType;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.SwordsmanClass;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeroServiceImplTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private HeroServiceImpl heroService;

    private Account sampleAccount;

    @BeforeEach
    void setUp(){
        sampleAccount = new Account();
        sampleAccount.setId("0");
        sampleAccount.setHeroClassType(HeroClassType.SWORDSMAN);
    }

    @Test
    void testGetHeroFromAccountDataMethod(){
        when(accountService.getAccountById("0")).thenReturn(sampleAccount);

        Hero hero = heroService.getHeroFromAccountData("0");
        assertTrue(hero.getHeroClass() instanceof SwordsmanClass);
    }
}
