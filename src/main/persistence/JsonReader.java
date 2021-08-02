package persistence;

import exceptions.DuplicateIdException;
import model.Inventory;
import model.Item;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/*
code borrowed and modified from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Inventory read() throws IOException, DuplicateIdException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        Inventory inv;
        try {
            inv = parseInventory(jsonObject);
        } catch (DuplicateIdException e) {
            throw new DuplicateIdException();
        }
        return inv;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses inventory from JSON object and returns it
    private Inventory parseInventory(JSONObject jsonObject) throws DuplicateIdException {
        Inventory inv = new Inventory();
        try {
            addItems(inv, jsonObject);
        } catch (DuplicateIdException e) {
            throw new DuplicateIdException();
        }
        return inv;
    }

    // MODIFIES: inv
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addItems(Inventory inv, JSONObject jsonObject) throws DuplicateIdException {
        JSONArray jsonArray = jsonObject.getJSONArray("itemList");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            try {
                addItem(inv, nextItem);
            } catch (DuplicateIdException e) {
                throw new DuplicateIdException();
            }
        }
    }

    // MODIFIES: inv
    // EFFECTS: parses item from JSON object and adds it to inv
    private void addItem(Inventory inv, JSONObject jsonObject) throws DuplicateIdException {
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        int stock = jsonObject.getInt("stock");
        int rop = jsonObject.getInt("rop");
        Item item = new Item(id, name, stock, rop);
        try {
            inv.addItem(item);
        } catch (DuplicateIdException e) {
            throw new DuplicateIdException();
        }
    }

}
