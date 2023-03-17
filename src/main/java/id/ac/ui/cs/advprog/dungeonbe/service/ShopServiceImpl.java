package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.core.shop.Shop;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.BuyItemResponseDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.ShopResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ShopServiceImpl implements ShopService{
    @Autowired
    private AccountService accountService;

    private static final String ACCOUNT_DOES_NOT_EXIST = "Account does not exist. Pls register your account first";
    private static final String BUY_SUCCESSFUL = "successfully buy/upgrade the item";
    private Shop shop;

    public ShopServiceImpl() {
        shop = Shop.getInstance();
    }

    @Override
    public BuyItemResponseDTO buyItem(String accountId, String itemId) {
        var responseDTO = new BuyItemResponseDTO();
        if (!accountService.accountExistsById(accountId)) {
            responseDTO.setError(true);
            responseDTO.setMessage(ACCOUNT_DOES_NOT_EXIST);
            return responseDTO;
        }

        var user = accountService.getAccountById(accountId);

        try {
            shop.addItemEquipment(user, itemId);
            CompletableFuture.runAsync(() -> accountService.saveAccount(user));
        } catch (RuntimeException error) {
            responseDTO.setError(Boolean.TRUE);
            responseDTO.setMessage(error.getMessage());
            return responseDTO;
        }

        responseDTO.setError(Boolean.FALSE);
        responseDTO.setMessage(BUY_SUCCESSFUL);
        return responseDTO;
    }

    @Override
    public ShopResponseDTO getShopItemList(String accountId) {
        var responseDTO = new ShopResponseDTO();

        if (!accountService.accountExistsById(accountId)) {
            responseDTO.setError(true);
            responseDTO.setMessage(ACCOUNT_DOES_NOT_EXIST);
            return responseDTO;
        }

        var user = accountService.getAccountById(accountId);

        List<String> listItemId = shop.getListItemId();
        List<String> listItemName = shop.getListItemName();
        List<String> listItemPrice = shop.getListItemPrice(user);

        responseDTO.setListItemId(listItemId);
        responseDTO.setListItemName(listItemName);
        responseDTO.setListItemPrice(listItemPrice);

        return responseDTO;
    }

}
