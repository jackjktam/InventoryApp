package persistence;

import exceptions.DuplicateIdException;
import model.Inventory;
import model.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*
code borrowed and modified from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Inventory inv = new Inventory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Inventory inv = new Inventory();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyInventory.json");
            writer.open();
            writer.write(inv);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyInventory.json");
            try {
                inv = reader.read();
            } catch (Exception e) {
                fail();
            }
            assertEquals(0, inv.getItemList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralInventory() {
        try {
            Inventory inv = new Inventory();
            inv.addItem(new Item(1, "a"));
            inv.addItem(new Item(2, "b"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralInventory.json");
            writer.open();
            writer.write(inv);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralInventory.json");
            inv = reader.read();
            List<Item> itemList = inv.getItemList();
            assertEquals(2, itemList.size());
            checkItem(1, "a", 0, 0, itemList.get(0));
            checkItem(2, "b", 0, 0, itemList.get(1));

        } catch (IOException | DuplicateIdException e) {
            fail("Exception should not have been thrown");
        }
    }
}