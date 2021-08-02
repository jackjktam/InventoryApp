package persistence;

import model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkItem(int id, String name, int stock, int rop, Item item) {
        assertEquals(id, item.getId());
        assertEquals(name, item.getName());
        assertEquals(stock, item.getStock());
        assertEquals(rop, item.getReorderPoint());

    }
}
