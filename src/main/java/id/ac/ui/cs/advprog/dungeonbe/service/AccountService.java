package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClassType;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;

public interface AccountService {
    Account getAccountById(String id);
    Account createAccount(String id, HeroClassType heroClassType);
    boolean accountExistsById(String id);
    void saveAccount(Account account);
}
