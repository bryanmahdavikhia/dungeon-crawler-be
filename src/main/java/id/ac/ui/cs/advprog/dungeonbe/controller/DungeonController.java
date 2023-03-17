package id.ac.ui.cs.advprog.dungeonbe.controller;

import id.ac.ui.cs.advprog.dungeonbe.model.dto.DungeonCommandDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.DungeonDTO;
import id.ac.ui.cs.advprog.dungeonbe.service.DungeonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/dungeon")
public class DungeonController {

    @Autowired
    private DungeonService dungeonService;

    @PostMapping("/check")
    public ResponseEntity<String> checkDungeon(@RequestBody DungeonCommandDTO dungeonCommandDTO){
        String id = dungeonCommandDTO.getId();
        if (dungeonService.getDungeonFromId(id) != null){
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/start")
    public ResponseEntity<DungeonDTO> startDungeon(@RequestBody DungeonCommandDTO dungeonCommandDTO){
        String id = dungeonCommandDTO.getId();
        return ResponseEntity.ok().body(dungeonService.startDungeon(id));
    }

    @PostMapping("/attack")
    public ResponseEntity<DungeonDTO> attack(@RequestBody DungeonCommandDTO dungeonCommandDTO){
        String id = dungeonCommandDTO.getId();
        return ResponseEntity.ok().body(dungeonService.attack(id));
    }

    @PostMapping("/item")
    public ResponseEntity<DungeonDTO> item(@RequestBody DungeonCommandDTO dungeonCommandDTO){
        String id = dungeonCommandDTO.getId();
        return ResponseEntity.ok().body(dungeonService.item(id));
    }

    @PostMapping("/useitem")
    public ResponseEntity<DungeonDTO> useItem(@RequestBody DungeonCommandDTO dungeonCommandDTO){
        String id = dungeonCommandDTO.getId();
        int idx = dungeonCommandDTO.getItemIndex();
        return ResponseEntity.ok().body(dungeonService.useItem(id, idx));
    }

    @PostMapping("/skill")
    public ResponseEntity<DungeonDTO> skill(@RequestBody DungeonCommandDTO dungeonCommandDTO){
        String id = dungeonCommandDTO.getId();
        return ResponseEntity.ok().body(dungeonService.skill(id));
    }

    @PostMapping("/leave")
    public ResponseEntity<DungeonDTO> leave(@RequestBody DungeonCommandDTO dungeonCommandDTO){
        String id = dungeonCommandDTO.getId();
        return ResponseEntity.ok().body(dungeonService.leave(id));
    }

    @PostMapping("/continue")
    public ResponseEntity<DungeonDTO> continues(@RequestBody DungeonCommandDTO dungeonCommandDTO){
        String id = dungeonCommandDTO.getId();
        return ResponseEntity.ok().body(dungeonService.continues(id));
    }

    @PostMapping("/timeout")
    public ResponseEntity<DungeonDTO> timeout(@RequestBody DungeonCommandDTO dungeonCommandDTO){
        String id = dungeonCommandDTO.getId();
        return ResponseEntity.ok().body(dungeonService.timeout(id));
    }

}

