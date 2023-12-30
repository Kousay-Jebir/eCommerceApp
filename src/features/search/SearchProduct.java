package features.search;

import features.utilityClasses.Collection;
import features.productManagement.Product;

public class SearchProduct {
    public static Product search(String searchInput, Collection<Product> products) {
        Product filteredProduct = null;

        //logic to apply the search
        for (Product product : products.getCollection()) {
            if (product.getProductLabel().equalsIgnoreCase(searchInput))
                filteredProduct = product;
        }
        //return found product if found else it return null
        return filteredProduct;

    }
}
