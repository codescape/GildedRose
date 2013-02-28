import java.util.ArrayList;
import java.util.List;


public class GildedRose {

    private static List<Item> items = null;

    public static void main(String[] args) {
        items = new ArrayList<Item>();
        items.add(createItem("+5 Dexterity Vest", 10, 20));
        items.add(createItem("Aged Brie", 2, 0));
        items.add(createItem("Elixir of the Mongoose", 5, 7));
        items.add(createItem("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(createItem("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(createItem("Conjured Mana Cake", 3, 6));
        printItems(0);
        updateAllItems();
        printItems(1);
    }

    private static Item createItem(String name, int sellIn, int quality) {
        if ("Sulfuras, Hand of Ragnaros".equals(name)) {
            return new LegendaryItem(name, sellIn, quality);
        } else {
            return new Item(name, sellIn, quality);
        }
    }

    private static void updateAllItems() {
        for (Item item : items) {
            if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
                updateItem(item);
            }
        }
    }

    private static void updateItem(Item item) {
        if ("Aged Brie".equals(item.getName())) {
            updateAgedBrie(item);
        } else if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
            updateBackstagePass(item);
        } else {
            updateCommonItem(item);
        }
    }

    private static void updateCommonItem(Item item) {
        reduceQuality(item);
        reduceSellIn(item);
        if (item.getSellIn() < 0) {
            reduceQuality(item);
        }
    }

    private static void updateBackstagePass(Item item) {
        increaseQualityForBackstagePasses(item);
        reduceSellIn(item);
        if (item.getSellIn() < 0) {
            zeroQuality(item);
        }
    }

    private static void updateAgedBrie(Item item) {
        increaseQuality(item);
        reduceSellIn(item);
        if (item.getSellIn() < 0) {
            increaseQuality(item);
        }
    }

    private static void zeroQuality(Item item) {
        item.setQuality(item.getQuality() - item.getQuality());
    }

    private static void increaseQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    private static void reduceSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private static void printItems(int days) {
        System.out.println("After " + days + " days:");
        for (Item item : items) {
            System.out.println(String.format("%40s %5s %5s", item.getName(), item.getQuality(), item.getSellIn()));
        }
    }

    private static void increaseQualityForBackstagePasses(Item item) {
        increaseQuality(item);
        if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
            if (item.getSellIn() < 11) {
                increaseQuality(item);
            }
            if (item.getSellIn() < 6) {
                increaseQuality(item);
            }
        }
    }

    private static void reduceQuality(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }

}