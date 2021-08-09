package model;

import exceptions.InsufficientStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Item item;

    @BeforeEach
    public void beforeEach() {
        item = new Item(0, "");
    }

    @Test
    public void testDecreaseStockTypical() {
        Item item = new Item(0, "", 5);
        try {
            item.decreaseStock(-1);
            assertEquals(4, item.getStock());
        } catch (InsufficientStockException e) {
            fail();
        }
    }

    @Test
    public void testDecreaseStockInsufficientStock() {
        Item item = new Item(0, "", 5);
        try {
            item.decreaseStock(-6);
            fail();
        } catch (InsufficientStockException e) {
        }
    }

    @Test
    public void testNeedsRestockTrue() {
        Item item1 = new Item(0, "", 5, 5);
        assertTrue(item1.needsRestock());
        Item item2 = new Item(0, "", 0);
        assertTrue(item2.needsRestock());
    }

    @Test
    public void testNeedsRestockFalse() {
        Item item3 = new Item(0, "", 1);
        assertFalse(item3.needsRestock());
        Item item4 = new Item(0, "", 5, 3);
        assertFalse(item4.needsRestock());
    }

    @Test
    public void testIncreaseStock() {
        item.increaseStock(5);
        assertEquals(5, item.getStock());
    }
}
