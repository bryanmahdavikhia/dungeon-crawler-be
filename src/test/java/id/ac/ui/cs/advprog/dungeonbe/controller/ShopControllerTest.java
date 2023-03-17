package id.ac.ui.cs.advprog.dungeonbe.controller;

import id.ac.ui.cs.advprog.dungeonbe.model.dto.BuyItemDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.BuyItemResponseDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.ShopDTO;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.ShopResponseDTO;
import id.ac.ui.cs.advprog.dungeonbe.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ShopController.class)
class ShopControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopService shopService;

    @Test
    void testBuyItem() throws Exception {
        String dummyAccountId = "Dummy account id";
        String dummyItemId = "Dummy item id";
        String dummyMessage = "dummy message";

        BuyItemResponseDTO expect = new BuyItemResponseDTO();
        expect.setMessage(dummyMessage);
        when(shopService.buyItem(dummyAccountId, dummyItemId)).thenReturn(expect);

        BuyItemDTO req = new BuyItemDTO();
        req.setAccountId(dummyAccountId);
        req.setItemId(dummyItemId);

        mockMvc.perform(post("/shop/buy")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(dummyMessage));
    }

    @Test
    void testGetShopItemList() throws Exception {
        String dummyAccountId = "Dummy account id";
        String dummyMessage = "dummy message";

        ShopResponseDTO expect = new ShopResponseDTO();
        expect.setMessage(dummyMessage);
        when(shopService.getShopItemList(dummyAccountId)).thenReturn(expect);

        ShopDTO req = new ShopDTO();
        req.setAccountId(dummyAccountId);

        mockMvc.perform(get("/shop/list")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(dummyMessage));
    }
}
