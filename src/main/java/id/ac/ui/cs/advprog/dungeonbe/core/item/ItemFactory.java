package id.ac.ui.cs.advprog.dungeonbe.core.item;

public class ItemFactory {

    public Item createItem(int id, int dungeonLevel){
        Item item = null;

        if (id == 0) {
            item = new HealthPotion(dungeonLevel);
        }
        else if (id == 1) {
            item = new ManaPotion(dungeonLevel);
        }

        return item;
    }

}
