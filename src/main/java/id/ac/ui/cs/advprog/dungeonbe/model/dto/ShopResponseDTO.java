package id.ac.ui.cs.advprog.dungeonbe.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShopResponseDTO {
    Boolean error = false;
    String message;
    List<String> listItemId;
    List<String> listItemName;
    List<String> listItemPrice;
}
