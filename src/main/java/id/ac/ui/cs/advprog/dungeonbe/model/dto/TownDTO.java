package id.ac.ui.cs.advprog.dungeonbe.model.dto;

import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import lombok.Data;

import java.util.List;

@Data
public class TownDTO {
    List<Account> leaderboard;
}
