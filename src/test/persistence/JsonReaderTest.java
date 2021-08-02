package persistence;

import exceptions.DuplicateIdException;
import model.Inventory;
import model.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
code borrowed and modified from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Inventory inv = reader.read();
            fail("IOException expected");
        } catch (IOException | DuplicateIdException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyInventory() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyInventory.json");
        try {
            Inventory inv = reader.read();
            assertEquals(0, inv.getItemList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (DuplicateIdException e) {
            fail("Duplicate ID found");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralInventory.json");
        try {
            Inventory inv = reader.read();
            List<Item> itemList = inv.getItemList();
            assertEquals(2, itemList.size());
            checkItem(1, "a", 0, 0, itemList.get(0));
            checkItem(2, "b", 20, 5, itemList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (DuplicateIdException e) {
            fail("Duplicate Id found");
        }
    }

    @Test
    void testReaderDuplicateId() {
        JsonReader reader = new JsonReader("./data/testReaderDuplicateId.json");
        try {
            Inventory inv = reader.read();
            fail("Expected duplicate id exception");
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (DuplicateIdException e) {
            // pass
        }
    }
}
