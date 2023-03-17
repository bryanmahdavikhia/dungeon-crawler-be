package id.ac.ui.cs.advprog.dungeonbe.controller;

import id.ac.ui.cs.advprog.dungeonbe.model.dto.AdminCommandDTO;
import id.ac.ui.cs.advprog.dungeonbe.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/health")
    public ResponseEntity<String> setHealth(@RequestBody AdminCommandDTO adminCommandDTO){
        String id = adminCommandDTO.getId();
        int health = adminCommandDTO.getValue();

        boolean success = adminService.setHealth(id, health);
        if (success)
            return ResponseEntity.ok().body(null);
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/mana")
    public ResponseEntity<String> setMana(@RequestBody AdminCommandDTO adminCommandDTO){
        String id = adminCommandDTO.getId();
        int mana = adminCommandDTO.getValue();

        boolean success = adminService.setMana(id, mana);
        if (success)
            return ResponseEntity.ok().body(null);
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/attack")
    public ResponseEntity<String> setAttack(@RequestBody AdminCommandDTO adminCommandDTO){
        String id = adminCommandDTO.getId();
        int attack = adminCommandDTO.getValue();

        boolean success = adminService.setAttack(id, attack);
        if (success)
            return ResponseEntity.ok().body(null);
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/defense")
    public ResponseEntity<String> setDefense(@RequestBody AdminCommandDTO adminCommandDTO){
        String id = adminCommandDTO.getId();
        int defense = adminCommandDTO.getValue();

        boolean success = adminService.setDefense(id, defense);
        if (success)
            return ResponseEntity.ok().body(null);
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/gold")
    public ResponseEntity<String> setGold(@RequestBody AdminCommandDTO adminCommandDTO){
        String id = adminCommandDTO.getId();
        int gold = adminCommandDTO.getValue();

        boolean success = adminService.setGold(id, gold);
        if (success)
            return ResponseEntity.ok().body(null);
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/weaponlevel")
    public ResponseEntity<String> setWeaponLevel(@RequestBody AdminCommandDTO adminCommandDTO){
        String id = adminCommandDTO.getId();
        int weaponLevel = adminCommandDTO.getValue();

        boolean success = adminService.setWeaponLevel(id, weaponLevel);
        if (success)
            return ResponseEntity.ok().body(null);
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/armorlevel")
    public ResponseEntity<String> setArmorLevel(@RequestBody AdminCommandDTO adminCommandDTO){
        String id = adminCommandDTO.getId();
        int armorLevel = adminCommandDTO.getValue();

        boolean success = adminService.setArmorLevel(id, armorLevel);
        if (success)
            return ResponseEntity.ok().body(null);
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/heroclasstype")
    public ResponseEntity<String> setHeroClassType(@RequestBody AdminCommandDTO adminCommandDTO){
        String id = adminCommandDTO.getId();
        var heroClassType = adminCommandDTO.getHeroClassType();

        boolean success = adminService.setHeroClassType(id, heroClassType);
        if (success)
            return ResponseEntity.ok().body(null);
        return ResponseEntity.badRequest().body(null);
    }
}
