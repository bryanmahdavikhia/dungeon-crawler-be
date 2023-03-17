package id.ac.ui.cs.advprog.dungeonbe.controller;

import id.ac.ui.cs.advprog.dungeonbe.model.dto.TownDTO;
import id.ac.ui.cs.advprog.dungeonbe.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/town")
public class TownController {

    @Autowired
    private TownService townService;

    @PostMapping("/leaderboard/")
    public ResponseEntity<TownDTO> seeLeaderboard(){
        return ResponseEntity.ok().body(townService.setLeaderboard());
    }
}
