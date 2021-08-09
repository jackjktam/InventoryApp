package model;

import exceptions.DuplicateIdException;
import exceptions.InsufficientStockException;
import exceptions.ItemNotFoundException;
import exceptions.NegativeAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    Inventory inv;

    @BeforeEach
    public void beforeEach() {
        inv = new Inventory();
        try {
            inv.addItem(0, "");
            inv.addItem(1, "", 5);
        } catch (DuplicateIdException e) {
        } catch (NegativeAmountException e) {
        }
    }

    @Test
    public void testFindItemFound() {
        try {
            Item i = inv.findItem(0);
            assertEquals(0, i.getId());
            assertEquals("", i.getName());
        } catch (ItemNotFoundException e) {
            fail();
        }
    }

    @Test
    public void testFindItemNotFound() {
        try {
            inv.findItem(100);
            fail();
        } catch (ItemNotFoundException e) {
        }
    }

    @Test
    public void testGetItemCount() {
        try {
            int count = inv.getItemCount(0);
            assertEquals(0, count);
        } catch (ItemNotFoundException e) {
            fail();
        }
    }

    @Test
    public void testGetItemCountItemNotFound() {
        try {
            int count = inv.getItemCount(100);
            fail();
        } catch (ItemNotFoundException e) {
        }
    }

    @Test
    public void testDecreaseStockTypical() {
        try {
            inv.decreaseStock(1, -1);
            assertEquals(4, inv.findItem(1).getStock());
        } catch (ItemNotFoundException e) {
            fail();
        } catch (NegativeAmountException e) {
            fail();
        } catch (InsufficientStockException e) {
            fail();
        }
    }

    @Test
    public void testDecreaseStockItemNotFound() {
        try {
            inv.decreaseStock(100, 1);
            fail();
        } catch (ItemNotFoundException e) {
        } catch (NegativeAmountException e) {
            fail();
        } catch (InsufficientStockException e) {
            fail();
        }
    }

    @Test
    public void testDecreaseStockInsufficientStock() {
        try {
            inv.decreaseStock(1, -100);
            fail();
        } catch (ItemNotFoundException e) {
            fail();
        } catch (NegativeAmountException e) {
            fail();
        } catch (InsufficientStockException e) {
        }
    }

    @Test
    public void getLowStockItems() {
        ArrayList<Item> lsi = inv.getLowStockItems();
        assertEquals(0, lsi.get(0).getId());
        assertEquals(1, lsi.size());
    }

    @Test
    public void testAddItemIdName() {
        try {
            inv.addItem(2, "");
            assertEquals(2, inv.getItemList().get(2).getId());
        } catch (DuplicateIdException e) {
            fail();
        }
    }

    @Test
    public void testAddItemIdNameDuplicateId() {
        try {
            inv.addItem(1, "");
            fail();
        } catch (DuplicateIdException e) {

        }
    }

    @Test
    public void testAddItemIdNameStock() {
        try {
            inv.addItem(2, "", 10);
            assertEquals(10, inv.getItemList().get(2).getStock());
        } catch (NegativeAmountException e) {
            fail();
        } catch (DuplicateIdException e) {
            fail();
        }
    }

    @Test
    public void testAddItemIdNameStockNegativeAmount() {
        try {
            inv.addItem(2, "", -1);
            fail();
        } catch (NegativeAmountException e) {
        } catch (DuplicateIdException e) {
            fail();
        }
    }

    @Test
    public void testAddItemIdNameStockDuplicateId() {
        try {
            inv.addItem(1, "", 0);
            fail();
        } catch (NegativeAmountException e) {
            fail();
        } catch (DuplicateIdException e) {
        }
    }

    @Test
    public void testAddItemIdNameStockRop() {
        try {
            inv.addItem(2, "", 0, 5);
            assertEquals(5, inv.getItemList().get(2).getReorderPoint());
        } catch (NegativeAmountException e) {
            fail();
        } catch (DuplicateIdException e) {
            fail();
        }
    }

    @Test
    public void testAddItemIdNameStockRopDuplicateId() {
        try {
            inv.addItem(1, "", 0, 5);
            fail();
        } catch (NegativeAmountException e) {
            fail();
        } catch (DuplicateIdException e) {
        }
    }

    @Test
    public void testAddItemIdNameStockRopNegativeStock() {
        try {
            inv.addItem(2, "", -5, 5);
            fail();
        } catch (NegativeAmountException e) {
        } catch (DuplicateIdException e) {
            fail();
        }
    }

    @Test
    public void testAddItemIdNameStockRopNegativeRop() {
        try {
            inv.addItem(2, "", 0, -5);
            fail();
        } catch (NegativeAmountException e) {
        } catch (DuplicateIdException e) {
            fail();
        }
    }








}
