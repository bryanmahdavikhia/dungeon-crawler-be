package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClassType;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccountById(String id) {
        return accountRepository.findAccountById(id);
    }

    @Override
    public Account createAccount(String id, HeroClassType heroClassType) {
        var account = new Account();
        account.setId(id);
        account.setHeroClassType(heroClassType);
        initializeAccount(account);
        accountRepository.save(account);
        return account;
    }

    @Override
    public boolean accountExistsById(String id) {
        return accountRepository.existsById(id);
    }


    private void initializeAccount(Account account){
        account.setMaxHealth(100);
        account.setMaxMana(100);
        account.setAttack(80);
        account.setDefense(50);
        account.setExp(0);
        account.setGold(0);
        account.setLevel(1);
        account.setDungeonLevel(0);
        account.setWeaponLevel(0);
        account.setArmorLevel(0);
        account.setItems("");
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}
