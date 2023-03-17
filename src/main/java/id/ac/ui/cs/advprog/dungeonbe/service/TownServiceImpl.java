package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.TownDTO;
import id.ac.ui.cs.advprog.dungeonbe.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownServiceImpl implements TownService{

    @Autowired
    AccountRepository accountRepository;
    @Override
    public TownDTO setLeaderboard() {
        List<Account> leaderboards = accountRepository.findAll(Sort.by(Sort.Direction.DESC, "DungeonLevel"));
        var townDTO = new TownDTO();
        var length = 10;
        if (leaderboards.size() < 10){
            length = leaderboards.size();
        }
        townDTO.setLeaderboard(leaderboards.subList(0, length));

        return townDTO;
    }
}
