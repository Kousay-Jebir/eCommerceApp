package features.search;

import features.utilityClasses.Collection;
import features.productManagement.Camera;
import features.productManagement.Computer;
import features.productManagement.Product;

public class FilteredProducts {
    public static Collection<Product> filteredProducts(Collection<Product> products, int action, float minPrice,
            float maxPrice,
            String review) {
        Collection<Product> filteredProducts = new Collection<Product>();
        //action == 1 : user choser to filter by category (Computers only)
        if (action == 1) {
            for (Product product : products.getCollection()) {
                if (product instanceof Computer) {
                    filteredProducts.addCollectable(product);
                }
            }
        }
        //action=2 : filter by category (Cameras only)
        else if (action == 2) {
            for (Product product : products.getCollection()) {
                if (product instanceof Camera) {
                    filteredProducts.addCollectable(product);
                }
            }
        }

        else if (action == 3) { // filter by price range
            for (Product product : products.getCollection()) {
                if (product.getProductPrice() >= minPrice && product.getProductPrice() <= maxPrice) {
                    filteredProducts.addCollectable(product);
                }
            }
        }

        else { //filter by ratings
                 //TODO implement filter by rating
        }
        return filteredProducts;
    }

}
