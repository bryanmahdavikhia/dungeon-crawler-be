package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.core.dungeon.Dungeon;
import id.ac.ui.cs.advprog.dungeonbe.core.dungeon.DungeonImpl;
import id.ac.ui.cs.advprog.dungeonbe.core.dungeon.DungeonState;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.DungeonDTO;
import id.ac.ui.cs.advprog.dungeonbe.repository.AccountRepository;
import id.ac.ui.cs.advprog.dungeonbe.repository.DungeonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DungeonServiceImpl implements DungeonService{

    @Autowired
    private DungeonRepository dungeonRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HeroService heroService;

    @Override
    public DungeonDTO startDungeon(String id) {
        var hero = heroService.getHeroFromAccountData(id);
        Dungeon dungeon = new DungeonImpl(hero);
        dungeonRepository.add(id, dungeon);
        return new DungeonDTO(hero, dungeon.getEnemy(), DungeonState.NORMAL, new ArrayList<>());
    }

    @Override
    public Dungeon getDungeonFromId(String id) {
        return dungeonRepository.findByName(id);
    }

    @Override
    public DungeonDTO attack(String id) {
        var dungeon = getDungeonFromId(id);
        var dungeonDTO = dungeon.attack();

        if(dungeonDTO.getDungeonState() == DungeonState.DEATH || dungeonDTO.getDungeonState() == DungeonState.FINISH){
            var account = dungeon.getAccount();
            accountRepository.save(account);

            dungeonRepository.deleteById(id);
        }

        return dungeonDTO;
    }

    @Override
    public DungeonDTO item(String id) {
        var dungeon = getDungeonFromId(id);

        return dungeon.item();
    }

    @Override
    public DungeonDTO useItem(String id, int idx) {
        var dungeon = getDungeonFromId(id);
        var dungeonDTO = dungeon.useItem(idx);

        if(dungeonDTO.getDungeonState() == DungeonState.DEATH || dungeonDTO.getDungeonState() == DungeonState.FINISH){
            var account = dungeon.getAccount();
            accountRepository.save(account);

            dungeonRepository.deleteById(id);
        }
        return dungeonDTO;
    }

    @Override
    public DungeonDTO skill(String id) {
        var dungeon = getDungeonFromId(id);
        var dungeonDTO = dungeon.useSkill();


        if(dungeonDTO.getDungeonState() == DungeonState.DEATH || dungeonDTO.getDungeonState() == DungeonState.FINISH){
            var account = dungeon.getAccount();
            accountRepository.save(account);

            dungeonRepository.deleteById(id);
        }
        return dungeonDTO;
    }

    @Override
    public DungeonDTO leave(String id) {
        var dungeon = getDungeonFromId(id);
        var dungeonDTO = dungeon.heroLeave();

        var account = dungeon.getAccount();
        accountRepository.save(account);

        dungeonRepository.deleteById(id);
        return dungeonDTO;
    }

    @Override
    public DungeonDTO continues(String id) {
        var dungeon = getDungeonFromId(id);

        return dungeon.heroContinue();
    }

    @Override
    public DungeonDTO timeout(String id) {
        var dungeon = getDungeonFromId(id);
        var dungeonDTO = dungeon.heroTimeOut();

        var account = dungeon.getAccount();
        accountRepository.save(account);

        dungeonRepository.deleteById(id);
        return dungeonDTO;
    }
}
