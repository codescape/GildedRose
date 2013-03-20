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
        for (Item item : items) printItemTo(item, out);
        out.println();
    }

    private static void printItemTo(Item item, PrintStream out) {
        out.println(String.format("%45s %5s %5s", item.getName(), item.getSellIn(), item.getQuality()));
    }

    public static void updateQuality() {
        for (Item item : items) updateQualityFor(item);
    }

    private static void updateQualityFor(Item item) {
        ItemUpdater.updaterFor(item).update();
    }

    private abstract static class ItemUpdater {

        public static ItemUpdater updaterFor(Item item) {
            if (hasName(item, "Sulfuras, Hand of Ragnaros"))
                return new VoidUpdater(item);
            if (hasName(item, "Aged Brie"))
                return new IncrementQualityUpdater(item);
            if (hasName(item, "Backstage passes to a TAFKAL80ETC concert"))
                return new BackstagePassesUpdater(item);
            return new DecrementQualityUpdater(item);
        }

        private static boolean hasName(Item item, String name) {
            return name.equals(item.getName());
        }

        private Item item;

        protected ItemUpdater(Item item) {
            this.item = item;
        }


        protected void incrementQuality() {
            if (item.getQuality() < 50) item.setQuality(item.getQuality() + 1);
        }

        protected void decrementSellIn() {
            item.setSellIn(item.getSellIn() - 1);
        }

        public final void update() {
            preDecrementSellIn();
            decrementSellIn();
            postDecrementSellIn();
        }

        protected abstract void preDecrementSellIn();

        protected abstract void postDecrementSellIn();

        protected Item getItem() {
            return item;
        }
    }

    private static class BackstagePassesUpdater extends ItemUpdater {

        BackstagePassesUpdater(Item item) {
            super(item);
        }

        @Override
        protected void preDecrementSellIn() {
            incrementQuality();
            if (getItem().getSellIn() < 11) incrementQuality();
            if (getItem().getSellIn() < 6) incrementQuality();
        }

        @Override
        protected void postDecrementSellIn() {
            if (getItem().getSellIn() < 0) getItem().setQuality(0);
        }

    }

    private static class IncrementQualityUpdater extends ItemUpdater {

        IncrementQualityUpdater(Item item) {
            super(item);
        }

        @Override
        protected void preDecrementSellIn() {
            incrementQuality();
        }

        @Override
        protected void postDecrementSellIn() {
            if (getItem().getSellIn() < 0) incrementQuality();
        }

    }

    private static class DecrementQualityUpdater extends ItemUpdater {

        DecrementQualityUpdater(Item item) {
            super(item);
        }

        @Override
        protected void preDecrementSellIn() {
            decrementQuality();
        }

        @Override
        protected void postDecrementSellIn() {
            if (getItem().getSellIn() < 0) decrementQuality();
        }

        private void decrementQuality() {
            if (getItem().getQuality() > 0) getItem().setQuality(getItem().getQuality() - 1);
        }

    }

    private static class VoidUpdater extends ItemUpdater {

        VoidUpdater(Item item) {
            super(item);
        }

        @Override
        public void decrementSellIn() {
            // intentionally left blank
        }

        @Override
        protected void preDecrementSellIn() {
            // intentionally left blank
        }

        @Override
        protected void postDecrementSellIn() {
            // intentionally left blank
        }

    }

}
