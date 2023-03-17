package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClassType;
import id.ac.ui.cs.advprog.dungeonbe.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public boolean setHealth(String id, int health) {
        var account = accountService.getAccountById(id);
        if (account == null)
            return false;
        account.setMaxHealth(health);
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean setMana(String id, int mana) {
        var account = accountService.getAccountById(id);
        if (account == null)
            return false;
        account.setMaxMana(mana);
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean setAttack(String id, int attack) {
        var account = accountService.getAccountById(id);
        if (account == null)
            return false;
        account.setAttack(attack);
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean setDefense(String id, int defense) {
        var account = accountService.getAccountById(id);
        if (account == null)
            return false;
        account.setDefense(defense);
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean setGold(String id, int gold) {
        var account = accountService.getAccountById(id);
        if (account == null)
            return false;
        account.setGold(gold);
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean setWeaponLevel(String id, int weaponLevel) {
        var account = accountService.getAccountById(id);
        if (account == null)
            return false;
        account.setWeaponLevel(weaponLevel);
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean setArmorLevel(String id, int armorLevel) {
        var account = accountService.getAccountById(id);
        if (account == null)
            return false;
        account.setArmorLevel(armorLevel);
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean setHeroClassType(String id, HeroClassType heroClassType) {
        var account = accountService.getAccountById(id);
        if (account == null)
            return false;
        account.setHeroClassType(heroClassType);
        accountRepository.save(account);
        return true;
    }
}
