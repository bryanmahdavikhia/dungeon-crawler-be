package id.ac.ui.cs.advprog.dungeonbe.controller;

import id.ac.ui.cs.advprog.dungeonbe.model.dto.BuyItemDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.BuyItemResponseDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.ShopDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.ShopResponseDTO;
import id.ac.ui.cs.advprog.dungeonbe.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @PostMapping("/buy")
    public ResponseEntity<BuyItemResponseDTO> buyItem(@RequestBody BuyItemDTO buyItemDTO){
        String accountId = buyItemDTO.getAccountId();
        String itemId = buyItemDTO.getItemId();
        return ResponseEntity.ok().body(shopService.buyItem(accountId, itemId));
    }

    @GetMapping("/list")
    public ResponseEntity<ShopResponseDTO> getShopItemList(@RequestBody ShopDTO shopItemDTO){
        String accountId = shopItemDTO.getAccountId();
        return ResponseEntity.ok().body(shopService.getShopItemList(accountId));
    }

}
