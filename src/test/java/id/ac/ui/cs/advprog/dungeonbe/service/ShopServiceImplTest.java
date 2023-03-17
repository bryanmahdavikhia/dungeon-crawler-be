package id.ac.ui.cs.advprog.dungeonbe.service;

import id.ac.ui.cs.advprog.dungeonbe.core.shop.Shop;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.BuyItemResponseDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.ShopResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShopServiceImplTest {

    @Mock
    private AccountService accountServiceMock;

    @Mock
    private Shop mockShop;

    @InjectMocks
    private ShopServiceImpl shopService;

    private final String dummyAccountId = "dummy account id";
    private final String dummyItemId = "dummy item id";

    private Account sampleAccount;

    @BeforeEach
    void setUp() {
        sampleAccount = new Account();
    }

    @Test
    void testBuyItemWhenAccountNotExist() {
        when(accountServiceMock.accountExistsById(dummyAccountId)).thenReturn(Boolean.FALSE);

        BuyItemResponseDTO result = shopService.buyItem(dummyAccountId, dummyItemId);
        assertTrue(result.getError());
        assertEquals("Account does not exist. Pls register your account first", result.getMessage());
    }

    @Test
    void testBuyItemWhenErrorWhenAddItemEquipment() {
        when(accountServiceMock.accountExistsById(dummyAccountId)).thenReturn(Boolean.TRUE);
        when(accountServiceMock.getAccountById(dummyAccountId)).thenReturn(sampleAccount);
        String dummyError = "Dummy error message";
        doThrow(new RuntimeException(dummyError)).when(mockShop).addItemEquipment(sampleAccount, dummyItemId);

        BuyItemResponseDTO result = shopService.buyItem(dummyAccountId, dummyItemId);
        assertTrue(result.getError());
        assertEquals(dummyError, result.getMessage());
    }

    @Test
    void testBuyItemWhenErrorWhenSuccessful() {
        when(accountServiceMock.accountExistsById(dummyAccountId)).thenReturn(Boolean.TRUE);
        when(accountServiceMock.getAccountById(dummyAccountId)).thenReturn(sampleAccount);
        doNothing().when(mockShop).addItemEquipment(sampleAccount, dummyItemId);

        BuyItemResponseDTO result = shopService.buyItem(dummyAccountId, dummyItemId);
        assertFalse(result.getError());
        assertEquals("successfully buy/upgrade the item", result.getMessage());
    }

    @Test
    void testGetShopItemListWhenAccountNotExist() {
        when(accountServiceMock.accountExistsById(dummyAccountId)).thenReturn(Boolean.FALSE);

        ShopResponseDTO result = shopService.getShopItemList(dummyAccountId);
        assertTrue(result.getError());
        assertEquals("Account does not exist. Pls register your account first", result.getMessage());
    }

    @Test
    void testGetShopItemListWhenSuccessful() {
        when(accountServiceMock.accountExistsById(dummyAccountId)).thenReturn(Boolean.TRUE);
        when(accountServiceMock.getAccountById(dummyAccountId)).thenReturn(sampleAccount);
        List<String> sampleItemId = new ArrayList<>();
        sampleItemId.add("0");
        List<String> sampleItemName = new ArrayList<>();
        sampleItemName.add("sample name");
        List<String> sampleItemPrice = new ArrayList<>();
        sampleItemPrice.add("1000");
        when(mockShop.getListItemId()).thenReturn(sampleItemId);
        when(mockShop.getListItemName()).thenReturn(sampleItemName);
        when(mockShop.getListItemPrice(sampleAccount)).thenReturn(sampleItemPrice);

        ShopResponseDTO result = shopService.getShopItemList(dummyAccountId);
        assertFalse(result.getError());
        assertEquals(sampleItemId, result.getListItemId());
        assertEquals(sampleItemName, result.getListItemName());
        assertEquals(sampleItemPrice, result.getListItemPrice());
    }

}
