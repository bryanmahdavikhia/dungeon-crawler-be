package id.ac.ui.cs.advprog.dungeonbe.core.item;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;

public interface Item {
    String useItem(Hero hero);
    int getId();
}
