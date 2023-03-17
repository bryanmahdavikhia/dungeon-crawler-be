package id.ac.ui.cs.advprog.dungeonbe.controller;

import id.ac.ui.cs.advprog.dungeonbe.model.dto.RoomDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.UpgradeStatsDTO;
import id.ac.ui.cs.advprog.dungeonbe.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/upgrade-attack")
    public ResponseEntity<UpgradeStatsDTO> upgradeAttack(@RequestBody RoomDTO roomDTO){
        String id = roomDTO.getId();
        var upgradeStatsDTO = roomService.upgradeAttack(id);
        return ResponseEntity.ok().body(upgradeStatsDTO);
    }

    @PostMapping("/upgrade-defense")
    public ResponseEntity<UpgradeStatsDTO> upgradeDefense(@RequestBody RoomDTO roomDTO){
        String id = roomDTO.getId();
        var upgradeStatsDTO = roomService.upgradeDefense(id);
        return ResponseEntity.ok().body(upgradeStatsDTO);
    }

    @PostMapping("/upgrade-mana")
    public ResponseEntity<UpgradeStatsDTO> upgradeMana(@RequestBody RoomDTO roomDTO){
        String id = roomDTO.getId();
        var upgradeStatsDTO = roomService.upgradeMana(id);
        return ResponseEntity.ok().body(upgradeStatsDTO);
    }

}
