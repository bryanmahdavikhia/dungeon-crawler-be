package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.model.dto.UpgradeStatsDTO;
import id.ac.ui.cs.advprog.dungeonbe.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    public UpgradeStatsDTO upgradeAttack(String id){
        var account = accountService.getAccountById(id);
        var accountGold = account.getGold();
        var upgradeStatsDTO = new UpgradeStatsDTO();

        if (accountGold >= 100){
            var accountAttack = account.getAttack();

            account.setGold(accountGold-100);
            account.setAttack(accountAttack + 15);
            accountRepository.save(account);

            upgradeStatsDTO.setAccount(account);
            upgradeStatsDTO.setAdded(true);

            return upgradeStatsDTO;
        }
        upgradeStatsDTO.setAccount(account);
        upgradeStatsDTO.setAdded(false);

        return upgradeStatsDTO;
    }

    public UpgradeStatsDTO upgradeDefense(String id){
        var account = accountService.getAccountById(id);
        var accountGold = account.getGold();
        var upgradeStatsDTO = new UpgradeStatsDTO();

        if (accountGold >= 100){
            var accountDefense = account.getDefense();

            account.setGold(accountGold-100);
            account.setDefense(accountDefense + 15);
            accountRepository.save(account);

            upgradeStatsDTO.setAccount(account);
            upgradeStatsDTO.setAdded(true);

            return upgradeStatsDTO;
        }
        upgradeStatsDTO.setAccount(account);
        upgradeStatsDTO.setAdded(false);

        return upgradeStatsDTO;
    }

    public UpgradeStatsDTO upgradeMana(String id){
        var account = accountService.getAccountById(id);
        var accountGold = account.getGold();
        var upgradeStatsDTO = new UpgradeStatsDTO();

        if (accountGold >= 100){
            var accountMana = account.getMaxMana();

            account.setGold(accountGold-100);
            account.setMaxMana(accountMana + 15);
            accountRepository.save(account);

            upgradeStatsDTO.setAccount(account);
            upgradeStatsDTO.setAdded(true);

            return upgradeStatsDTO;
        }
        upgradeStatsDTO.setAccount(account);
        upgradeStatsDTO.setAdded(false);

        return upgradeStatsDTO;
    }
}
