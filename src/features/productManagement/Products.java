package features.productManagement;

import java.util.ArrayList;

import features.userAuth.User;

public class Products {
    private ArrayList<Product> productList;

    Products() {
        productList = new ArrayList<Product>();
    }

    public ArrayList<Product> getProducts() {
        return productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }
}
