package features.utilityClasses;

import java.util.ArrayList;

import features.productManagement.Product;
import features.userAuth.User;

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

    public void listCollection() {
        for (T user : items) {
            user.toString();
        }
    }

    public int findUserById(int ID) { // for simplicity reasons we trust that the admin has entered a valid user ID
        int i = 0;
        for (T user : items) {
            if (((User) user).getUserID() == ID)
                return i;
            i++;
        }
        return -1;//not found
    }

    public int findProductById(int ID) { // for simplicity reasons we trust that the admin has entered a valid user ID
        int i = 0;
        for (T product : items) {
            if (((Product) product).getProductID() == ID)
                return i;
            i++;
        }
        return -1;//not found
    }
    // Other methods...
}
