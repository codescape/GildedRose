import java.util.ArrayList;
import java.util.List;


public class GildedRose {

    private static List<Item> items = null;

    public static void main(String[] args) {
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));

        printItems(0);
        updateQuality();
        printItems(1);
    }

    private static void updateQuality() {
        for (Item item : items) {
            if (("Aged Brie".equals(item.getName())) || "Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
                increaseQuality(item);
                if (item.getQuality() < 50) {
                    increaseQualityForBackstagePasses(item);
                }
            } else {
                if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
                    reduceQuality(item);
                }
            }
            if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
                reduceSellIn(item);
            }
            if (item.getSellIn() < 0) {
                if ("Aged Brie".equals(item.getName())) {
                    increaseQuality(item);
                } else {
                    if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
                        item.setQuality(item.getQuality() - item.getQuality());
                    } else {
                        if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
                            reduceQuality(item);
                        }
                    }
                }
            }
        }
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