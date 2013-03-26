
public class ItemUpdater {

    static void updateQualityFor(Item item) {
        updateQualityBeforeUpdatingSellInFor(item);
        updateSellIn(item);
        updateQualityAfterUpdatingSellIn(item);
    }

    private static void updateQualityAfterUpdatingSellIn(Item item) {
        if (item.getSellIn() < 0) {
            if ("Aged Brie".equals(item.getName())) {
                increaseQuality(item);
            } else {
                if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
                    item.setQuality(item.getQuality() - item.getQuality());
                } else {
                    if (item.getQuality() > 0) {
                        if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
                            reduceQuality(item);
                        }
                    }
                }
            }
        }
    }

    private static void updateSellIn(Item item) {
        if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
            reduceSellIn(item);
        }
    }

    private static void updateQualityBeforeUpdatingSellInFor(Item item) {
        if (("Aged Brie".equals(item.getName())) || "Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
            increaseQuality(item);
            if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
                if (item.getSellIn() < 11) {
                    increaseQuality(item);
                }

                if (item.getSellIn() < 6) {
                    increaseQuality(item);
                }
            }
        } else {
            if (item.getQuality() > 0) {
                if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
                    reduceQuality(item);
                }
            }
        }
    }

    private static void reduceQuality(Item item) {
        item.setQuality(item.getQuality() - 1);
    }

    private static void reduceSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private static void increaseQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

}
