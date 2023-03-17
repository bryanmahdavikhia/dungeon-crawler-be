package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.model.dto.UpgradeStatsDTO;

public interface RoomService {
    UpgradeStatsDTO upgradeAttack(String id);
    UpgradeStatsDTO upgradeDefense(String id);
    UpgradeStatsDTO upgradeMana(String id);
}
