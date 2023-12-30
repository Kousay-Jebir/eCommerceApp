package features.shoppingCart;

import features.productManagement.Product;
import features.utilityClasses.Collection;

public class CartProduct {
    private Product assosiatedProduct;
    private int quantityInCart;
    private float totalPrice;

    //getters
    public float getTotalPrice() {
        return totalPrice;
    }

    public int getQuantityInCart() {
        return quantityInCart;
    }

    public Product getAssosiatedProduct() {
        return assosiatedProduct;
    }

    //Cart management methods (will be used in the Cart menu)
    public void incrementQuantityInCart(int quantity) {
        quantityInCart += quantity;
        totalPrice += assosiatedProduct.getProductPrice() * (1 - assosiatedProduct.getProductDiscountPercent() / 100);
    }

    public void decrementQuantityInCart(int quantity, Collection<CartProduct> cartProducts) {
        quantityInCart -= quantity;
        totalPrice -= assosiatedProduct.getProductPrice() * (1 - assosiatedProduct.getProductDiscountPercent() / 100)
                * quantityInCart;
        //Handle scenario where quantity = 0
        if (quantityInCart == 0)
            removeCartProduct(cartProducts);
    }

    public void removeCartProduct(Collection<CartProduct> cartProducts) {
        cartProducts.removeCollectable(this);
    }

    public String toString() {
        System.out.println("Product's name: " + assosiatedProduct.productCategory());
        System.out.println("Product's name: " + assosiatedProduct.getProductLabel());
        System.out.println("Price per unit: " + assosiatedProduct.getProductPrice());
        System.out.println("Quantity in cart: " + quantityInCart);
        System.out.println("Total price: " + totalPrice);
        return "";
    }

    //Constructor
    public CartProduct(Product assosiatedProduct, int quantityInCart) {
        this.assosiatedProduct = assosiatedProduct;
        this.quantityInCart = quantityInCart;
        this.totalPrice = assosiatedProduct.getProductPrice()
                * (1 - assosiatedProduct.getProductDiscountPercent() / 100)
                * quantityInCart;
    }

}
