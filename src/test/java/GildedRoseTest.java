import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    private String expectedOutput = "Items in stock after 0 days:\n" +
            "                            +5 Dexterity Vest    10    20\n" +
            "                                    Aged Brie     2     0\n" +
            "                       Elixir of the Mongoose     5     7\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert    15    20\n" +
            "                           Conjured Mana Cake     3     6\n" +
            "Items in stock after 1 days:\n" +
            "                            +5 Dexterity Vest     9    19\n" +
            "                                    Aged Brie     1     1\n" +
            "                       Elixir of the Mongoose     4     6\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert    14    21\n" +
            "                           Conjured Mana Cake     2     5\n" +
            "Items in stock after 2 days:\n" +
            "                            +5 Dexterity Vest     8    18\n" +
            "                                    Aged Brie     0     2\n" +
            "                       Elixir of the Mongoose     3     5\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert    13    22\n" +
            "                           Conjured Mana Cake     1     4\n" +
            "Items in stock after 3 days:\n" +
            "                            +5 Dexterity Vest     7    17\n" +
            "                                    Aged Brie    -1     4\n" +
            "                       Elixir of the Mongoose     2     4\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert    12    23\n" +
            "                           Conjured Mana Cake     0     3\n" +
            "Items in stock after 4 days:\n" +
            "                            +5 Dexterity Vest     6    16\n" +
            "                                    Aged Brie    -2     6\n" +
            "                       Elixir of the Mongoose     1     3\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert    11    24\n" +
            "                           Conjured Mana Cake    -1     1\n" +
            "Items in stock after 5 days:\n" +
            "                            +5 Dexterity Vest     5    15\n" +
            "                                    Aged Brie    -3     8\n" +
            "                       Elixir of the Mongoose     0     2\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert    10    25\n" +
            "                           Conjured Mana Cake    -2     0\n" +
            "Items in stock after 6 days:\n" +
            "                            +5 Dexterity Vest     4    14\n" +
            "                                    Aged Brie    -4    10\n" +
            "                       Elixir of the Mongoose    -1     0\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert     9    27\n" +
            "                           Conjured Mana Cake    -3     0\n" +
            "Items in stock after 7 days:\n" +
            "                            +5 Dexterity Vest     3    13\n" +
            "                                    Aged Brie    -5    12\n" +
            "                       Elixir of the Mongoose    -2     0\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert     8    29\n" +
            "                           Conjured Mana Cake    -4     0\n" +
            "Items in stock after 8 days:\n" +
            "                            +5 Dexterity Vest     2    12\n" +
            "                                    Aged Brie    -6    14\n" +
            "                       Elixir of the Mongoose    -3     0\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert     7    31\n" +
            "                           Conjured Mana Cake    -5     0\n" +
            "Items in stock after 9 days:\n" +
            "                            +5 Dexterity Vest     1    11\n" +
            "                                    Aged Brie    -7    16\n" +
            "                       Elixir of the Mongoose    -4     0\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert     6    33\n" +
            "                           Conjured Mana Cake    -6     0\n" +
            "Items in stock after 10 days:\n" +
            "                            +5 Dexterity Vest     0    10\n" +
            "                                    Aged Brie    -8    18\n" +
            "                       Elixir of the Mongoose    -5     0\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert     5    35\n" +
            "                           Conjured Mana Cake    -7     0\n" +
            "Items in stock after 11 days:\n" +
            "                            +5 Dexterity Vest    -1     8\n" +
            "                                    Aged Brie    -9    20\n" +
            "                       Elixir of the Mongoose    -6     0\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert     4    38\n" +
            "                           Conjured Mana Cake    -8     0\n" +
            "Items in stock after 12 days:\n" +
            "                            +5 Dexterity Vest    -2     6\n" +
            "                                    Aged Brie   -10    22\n" +
            "                       Elixir of the Mongoose    -7     0\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert     3    41\n" +
            "                           Conjured Mana Cake    -9     0\n" +
            "Items in stock after 13 days:\n" +
            "                            +5 Dexterity Vest    -3     4\n" +
            "                                    Aged Brie   -11    24\n" +
            "                       Elixir of the Mongoose    -8     0\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert     2    44\n" +
            "                           Conjured Mana Cake   -10     0\n" +
            "Items in stock after 14 days:\n" +
            "                            +5 Dexterity Vest    -4     2\n" +
            "                                    Aged Brie   -12    26\n" +
            "                       Elixir of the Mongoose    -9     0\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert     1    47\n" +
            "                           Conjured Mana Cake   -11     0\n" +
            "Items in stock after 15 days:\n" +
            "                            +5 Dexterity Vest    -5     0\n" +
            "                                    Aged Brie   -13    28\n" +
            "                       Elixir of the Mongoose   -10     0\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert     0    50\n" +
            "                           Conjured Mana Cake   -12     0\n" +
            "Items in stock after 16 days:\n" +
            "                            +5 Dexterity Vest    -6     0\n" +
            "                                    Aged Brie   -14    30\n" +
            "                       Elixir of the Mongoose   -11     0\n" +
            "                   Sulfuras, Hand of Ragnaros     0    80\n" +
            "    Backstage passes to a TAFKAL80ETC concert    -1     0\n" +
            "                           Conjured Mana Cake   -13     0\n";

    @Test
    public void verifyItemList() {
        OutputStream outputStream = redirectSystemOutToOutputStream();
        GildedRose.main(null);
        for (int i = 1; i <= 16; i++) {
            GildedRose.printItemList(i);
            GildedRose.updateQuality();
        }
        assertEquals(expectedOutput, outputStream.toString());
    }

    private OutputStream redirectSystemOutToOutputStream() {
        OutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        return outputStream;
    }

}
