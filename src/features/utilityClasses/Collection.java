package features.utilityClasses;

import java.util.ArrayList;

public class Collection<T> {
    private ArrayList<T> items = new ArrayList<>();

    public ArrayList<T> getCollection() {
        return items;
    }

    public void addCollectable(T item) {
        items.add(item);
    }

    public void removeCollectable(T item) {
        items.remove(item);
    }

    // Other methods...
}
