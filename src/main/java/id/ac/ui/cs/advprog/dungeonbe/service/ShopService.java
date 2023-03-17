package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.model.dto.BuyItemResponseDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.ShopResponseDTO;

public interface ShopService {
    BuyItemResponseDTO buyItem(String accountId, String itemId);
    ShopResponseDTO getShopItemList(String accountId);
}
