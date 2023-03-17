package id.ac.ui.cs.advprog.dungeonbe.model.dto;

import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import lombok.Data;

@Data
public class UpgradeStatsDTO {
    Account account;
    boolean isAdded;
}
