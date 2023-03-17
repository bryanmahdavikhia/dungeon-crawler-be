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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AdminServiceImpl adminService;

    private Account sampleAccount;

    @BeforeEach
    void setUp(){
        sampleAccount = new Account();
        sampleAccount.setId("0");
    }

    @Test
    void testSetHealth(){
        when(accountService.getAccountById("0")).thenReturn(sampleAccount);

        boolean resp = adminService.setHealth("0", 10);
        assertTrue(resp);
        assertEquals(10, sampleAccount.getMaxHealth());
        verify(accountRepository).save(sampleAccount);
    }

    @Test
    void testSetHealthWhenAccountNotFound(){
        when(accountService.getAccountById("1")).thenReturn(null);

        boolean resp = adminService.setHealth("1", 10);
        assertFalse(resp);
    }

    @Test
    void testSetMana(){
        when(accountService.getAccountById("0")).thenReturn(sampleAccount);

        boolean resp = adminService.setMana("0", 10);
        assertTrue(resp);
        assertEquals(10, sampleAccount.getMaxMana());
        verify(accountRepository).save(sampleAccount);
    }

    @Test
    void testSetManaWhenAccountNotFound(){
        when(accountService.getAccountById("1")).thenReturn(null);

        boolean resp = adminService.setMana("1", 10);
        assertFalse(resp);
    }

    @Test
    void testSetAttack(){
        when(accountService.getAccountById("0")).thenReturn(sampleAccount);

        boolean resp = adminService.setAttack("0", 10);
        assertTrue(resp);
        assertEquals(10, sampleAccount.getAttack());
        verify(accountRepository).save(sampleAccount);
    }

    @Test
    void testSetAttackWhenAccountNotFound(){
        when(accountService.getAccountById("1")).thenReturn(null);

        boolean resp = adminService.setAttack("1", 10);
        assertFalse(resp);
    }

    @Test
    void testSetDefense(){
        when(accountService.getAccountById("0")).thenReturn(sampleAccount);

        boolean resp = adminService.setDefense("0", 10);
        assertTrue(resp);
        assertEquals(10, sampleAccount.getDefense());
        verify(accountRepository).save(sampleAccount);
    }

    @Test
    void testSetDefenseWhenAccountNotFound(){
        when(accountService.getAccountById("1")).thenReturn(null);

        boolean resp = adminService.setDefense("1", 10);
        assertFalse(resp);
    }

    @Test
    void testSetGold(){
        when(accountService.getAccountById("0")).thenReturn(sampleAccount);

        boolean resp = adminService.setGold("0", 10);
        assertTrue(resp);
        assertEquals(10, sampleAccount.getGold());
        verify(accountRepository).save(sampleAccount);
    }

    @Test
    void testSetGoldWhenAccountNotFound(){
        when(accountService.getAccountById("1")).thenReturn(null);

        boolean resp = adminService.setGold("1", 10);
        assertFalse(resp);
    }

    @Test
    void testSetWeaponLevel(){
        when(accountService.getAccountById("0")).thenReturn(sampleAccount);

        boolean resp = adminService.setWeaponLevel("0", 10);
        assertTrue(resp);
        assertEquals(10, sampleAccount.getWeaponLevel());
        verify(accountRepository).save(sampleAccount);
    }

    @Test
    void testSetWeaponLevelWhenAccountNotFound(){
        when(accountService.getAccountById("1")).thenReturn(null);

        boolean resp = adminService.setWeaponLevel("1", 10);
        assertFalse(resp);
    }

    @Test
    void testSetArmorLevel(){
        when(accountService.getAccountById("0")).thenReturn(sampleAccount);

        boolean resp = adminService.setArmorLevel("0", 10);
        assertTrue(resp);
        assertEquals(10, sampleAccount.getArmorLevel());
        verify(accountRepository).save(sampleAccount);
    }

    @Test
    void testSetArmorLevelWhenAccountNotFound(){
        when(accountService.getAccountById("1")).thenReturn(null);

        boolean resp = adminService.setArmorLevel("1", 10);
        assertFalse(resp);
    }

    @Test
    void testSetHeroClassType(){
        when(accountService.getAccountById("0")).thenReturn(sampleAccount);

        boolean resp = adminService.setHeroClassType("0", HeroClassType.MAGE);
        assertTrue(resp);
        assertEquals(HeroClassType.MAGE, sampleAccount.getHeroClassType());
        verify(accountRepository).save(sampleAccount);
    }

    @Test
    void testSetHeroClassTypeWhenAccountNotFound(){
        when(accountService.getAccountById("1")).thenReturn(null);

        boolean resp = adminService.setHeroClassType("1", HeroClassType.MAGE);
        assertFalse(resp);
    }
}
