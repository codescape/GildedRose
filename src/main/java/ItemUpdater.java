
public class ItemUpdater {

    private Item item;

    public ItemUpdater(Item item) {
        this.item = item;
    }

    static void updateQualityFor(Item item) {
        ItemUpdater itemUpdater = getItemUpdaterFor(item);
        itemUpdater.updateQualityBeforeUpdatingSellInFor(item);
        itemUpdater.updateSellIn(item);
        itemUpdater.updateQualityAfterUpdatingSellIn(item);
    }

    private static ItemUpdater getItemUpdaterFor(Item item) {
        return new ItemUpdater(item);
    }

    private void updateQualityAfterUpdatingSellIn(Item item) {
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

    private void updateSellIn(Item item) {
        if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
            reduceSellIn(item);
        }
    }

    private void updateQualityBeforeUpdatingSellInFor(Item item) {
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

    private void reduceQuality(Item item) {
        item.setQuality(item.getQuality() - 1);
    }

    private void reduceSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private void increaseQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

}
