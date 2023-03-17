package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroServiceImpl implements HeroService{

    @Autowired
    private AccountService accountService;

    @Override
    public Hero getHeroFromAccountData(String id) {
        var account = accountService.getAccountById(id);
        return new Hero(account);
    }
}
