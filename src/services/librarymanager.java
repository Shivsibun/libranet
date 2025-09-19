package services;

import java.util.*;

import models.*;

public class librarymanager {
    private Map<Integer, libraryitem> items = new HashMap<>();

    public void addItem(libraryitem item) {
        items.put(item.getId(), item);
    }
    public libraryitem getItem(int id) {
        return items.get(id);
    }
    public Collection<libraryitem> getAllItems() {
        return items.values();
    }
    public List<libraryitem> searchByType(Class<?> type) {
        List<libraryitem> result = new ArrayList<>();
        for (libraryitem item : items.values()) {
            if (type.isInstance(item)) {
                result.add(item);
            }
        }
        return result;
    }
}
