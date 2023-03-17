package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.UpgradeStatsDTO;
import id.ac.ui.cs.advprog.dungeonbe.repository.AccountRepository;
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
class RoomServiceImplTest {

    @Mock
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    private Account sampleAccount;

    @BeforeEach
    void setUp(){
        sampleAccount = new Account();
        sampleAccount.setId("0");
        sampleAccount.setGold(150);
    }

    @Test
    void testUpgradeAttack(){
        when(accountService.getAccountById("0")).thenReturn(sampleAccount);

        UpgradeStatsDTO upgradeStatsDTO = roomService.upgradeAttack("0");
        assertEquals(true, upgradeStatsDTO.isAdded());
        assertEquals(50, sampleAccount.getGold());
        assertEquals(15, sampleAccount.getAttack());
        verify(accountRepository).save(sampleAccount);

        upgradeStatsDTO = roomService.upgradeAttack("0");
        assertEquals(false, upgradeStatsDTO.isAdded());
        assertEquals(50, sampleAccount.getGold());
        assertEquals(15, sampleAccount.getAttack());
    }

    @Test
    void testUpgradeDefense(){
        when(accountService.getAccountById("0")).thenReturn(sampleAccount);

        UpgradeStatsDTO upgradeStatsDTO = roomService.upgradeDefense("0");
        assertEquals(true, upgradeStatsDTO.isAdded());
        assertEquals(50, sampleAccount.getGold());
        assertEquals(15, sampleAccount.getDefense());
        verify(accountRepository).save(sampleAccount);

        upgradeStatsDTO = roomService.upgradeDefense("0");
        assertEquals(false, upgradeStatsDTO.isAdded());
        assertEquals(50, sampleAccount.getGold());
        assertEquals(15, sampleAccount.getDefense());
    }

    @Test
    void testUpgradeMana(){
        when(accountService.getAccountById("0")).thenReturn(sampleAccount);

        UpgradeStatsDTO upgradeStatsDTO = roomService.upgradeMana("0");
        assertEquals(true, upgradeStatsDTO.isAdded());
        assertEquals(50, sampleAccount.getGold());
        assertEquals(15, sampleAccount.getMaxMana());
        verify(accountRepository).save(sampleAccount);

        upgradeStatsDTO = roomService.upgradeMana("0");
        assertEquals(false, upgradeStatsDTO.isAdded());
        assertEquals(50, sampleAccount.getGold());
        assertEquals(15, sampleAccount.getMaxMana());
    }
}
