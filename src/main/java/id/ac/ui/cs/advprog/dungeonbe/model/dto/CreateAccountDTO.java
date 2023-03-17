package id.ac.ui.cs.advprog.dungeonbe.model.dto;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClassType;
import lombok.Data;

@Data
public class CreateAccountDTO {
    String id;
    HeroClassType heroClassType;
}
