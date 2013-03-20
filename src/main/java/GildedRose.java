import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class GildedRose {

    private static List<Item> items = null;

    public static void main(String[] args) {
        System.out.println("OMGHAI!");
        init();
        updateQuality();
    }

    public static void init() {
        items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));
    }

    public static void printListTo(PrintStream out) {
        for (Item item : items) {
            printItemTo(item, out);
        }
        out.println();
    }

    private static void printItemTo(Item item, PrintStream out) {
        out.println(String.format("%45s %5s %5s", item.getName(), item.getSellIn(), item.getQuality()));
    }

    public static void updateQuality() {
        for (Item item : items) {
            updateQualityFor(item);
        }
    }

    private static void updateQualityFor(Item item) {
        ItemUpdater updater = ItemUpdater.updaterFor(item);
        updater.update(item);
    }

    private static boolean hasName(Item item, String name) {
        return name.equals(item.getName());
    }

    private static void decrementSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private static void decrementQuality(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    private static void incrementQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    private abstract static class ItemUpdater {

        public static ItemUpdater updaterFor(Item item) {
            if (hasName(item, "Sulfuras, Hand of Ragnaros"))
                return new VoidUpdater();
            if (hasName(item, "Aged Brie"))
                return new IncrementQualityUpdater();
            if (hasName(item, "Backstage passes to a TAFKAL80ETC concert"))
                return new BackstagePassesUpdater();
            return new DecrementQualityUpdater();
        }

        public abstract void update(Item item);

    }

    private static class BackstagePassesUpdater extends ItemUpdater {
        @Override
        public void update(Item item) {
            incrementQuality(item);
            if (item.getSellIn() < 11) {
                incrementQuality(item);
            }
            if (item.getSellIn() < 6) {
                incrementQuality(item);
            }
            decrementSellIn(item);
            if (item.getSellIn() < 0) {
                item.setQuality(0);
            }
        }
    }

    private static class IncrementQualityUpdater extends ItemUpdater {

        @Override
        public void update(Item item) {
            incrementQuality(item);
            decrementSellIn(item);
            if (item.getSellIn() < 0) {
                incrementQuality(item);
            }
        }

    }

    private static class DecrementQualityUpdater extends ItemUpdater {

        @Override
        public void update(Item item) {
            decrementQuality(item);
            decrementSellIn(item);
            if (item.getSellIn() < 0) {
                decrementQuality(item);
            }
        }
    }

    private static class VoidUpdater extends ItemUpdater {
        @Override
        public void update(Item item) {
            // intentionally left blank
        }
    }

}
