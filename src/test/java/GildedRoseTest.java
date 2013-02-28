import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    private String expectedResult = "After 0 days:\r\n" +
            "                       +5 Dexterity Vest    20    10\r\n" +
            "                               Aged Brie     0     2\r\n" +
            "                  Elixir of the Mongoose     7     5\r\n" +
            "              Sulfuras, Hand of Ragnaros    80     0\r\n" +
            "Backstage passes to a TAFKAL80ETC concert    20    15\r\n" +
            "                      Conjured Mana Cake     6     3\r\n" +
            "After 1 days:\r\n" +
            "                       +5 Dexterity Vest    19     9\r\n" +
            "                               Aged Brie     1     1\r\n" +
            "                  Elixir of the Mongoose     6     4\r\n" +
            "              Sulfuras, Hand of Ragnaros    80     0\r\n" +
            "Backstage passes to a TAFKAL80ETC concert    21    14\r\n" +
            "                      Conjured Mana Cake     5     2\r\n";

    @Test
    public void testThatGildedRoseReturnsSameItemValues() {
        OutputStream outputStream = interceptOutput();
        GildedRose.main(null);
        assertEquals(expectedResult, outputStream.toString());
    }

    private ByteArrayOutputStream interceptOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out;
    }

}
