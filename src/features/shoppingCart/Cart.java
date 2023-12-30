package features.shoppingCart;

import java.util.Scanner;

import features.productManagement.Product;
import features.userAuth.Customer;
import features.utilityClasses.Collection;

public class Cart {
    private Scanner sc = new Scanner(System.in);
    private Collection<CartProduct> cartItems = new Collection<CartProduct>();
    private float totalPrice = 0;

    public float getTotalPrice() {
        return totalPrice;
    }

    public Collection<CartProduct> getCartItems() {
        return cartItems;
    }

    public void addToCart(Product product, int quantity) {
        // Check if the product is already in the cart
        CartProduct existingProduct = findProductInCart(product);

        if (existingProduct != null) {
            // Product is already in the cart, update the quantity
            existingProduct.incrementQuantityInCart(quantity);
            totalPrice += existingProduct.getAssosiatedProduct().getProductPrice()
                    * (1 - existingProduct.getAssosiatedProduct().getProductDiscountPercent() / 100) * quantity;
        } else {
            // Product is not in the cart, add a new CartProduct
            CartProduct productToAdd = new CartProduct(product, quantity);
            cartItems.addCollectable(productToAdd);
            totalPrice = totalPrice + productToAdd.getTotalPrice();
        }
    }

    // Helper method to find a product in the cart
    private CartProduct findProductInCart(Product product) {
        for (CartProduct cartProduct : cartItems.getCollection()) {
            if (cartProduct.getAssosiatedProduct().equals(product)) {
                return cartProduct;
            }
        }
        return null; // Product not found in the cart
    }

    public int cartMenu() {
        System.out.println("You have " + cartItems.getCollection().size() + " total Items");
        System.out.println("Your total comes up to $" + totalPrice);
        System.out.println("&|-----------------------------------|&");
        cartItems.listCollection();
        System.out.println("&|-----------------------------------|&");

        System.out.println("Choose an action:");
        System.out.println("1. Add quantity");
        System.out.println("2. Lower quantity");
        System.out.println("3. Remove product");
        System.out.println("0. Exit");

        int choice = sc.nextInt();
        sc.nextLine(); // consume the newline character

        switch (choice) {
            case 1:
                addQuantity();
                break;
            case 2:
                lowerQuantity();
                break;
            case 3:
                removeProduct();
                break;
            case 0:
                System.out.println("Exiting cart menu.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return choice;
    }

    private void addQuantity() {
        System.out.println("Enter the number of the product to add quantity first product is number 1 :");
        int index = sc.nextInt();
        sc.nextLine(); // consume the newline character

        if (index - 1 >= 0 && index - 1 < cartItems.getCollection().size()) {
            CartProduct selectedProduct = cartItems.getCollection().get(index - 1);
            System.out.println("Enter the quantity to add:");
            int quantity = sc.nextInt();
            sc.nextLine(); // consume the newline character

            // Check if the requested quantity is available in stock
            if (quantity + selectedProduct.getQuantityInCart() <= selectedProduct.getAssosiatedProduct()
                    .getProductQuantity()) {
                selectedProduct.incrementQuantityInCart(quantity);
                totalPrice += selectedProduct.getAssosiatedProduct().getProductPrice()
                        * (1 - selectedProduct.getAssosiatedProduct().getProductDiscountPercent() / 100)
                        * quantity;
                System.out.println("Quantity added successfully!");
            } else {
                System.out.println("Not enough quantity in stock. Please choose a smaller quantity.");
            }
        } else {
            System.out.println("Invalid index. No product found.");
        }
    }

    private void lowerQuantity() {
        System.out.println("Enter the number of the product to lower quantity first product is number 1 :");
        int index = sc.nextInt();
        sc.nextLine(); // consume the newline character

        if (index - 1 >= 0 && index - 1 < cartItems.getCollection().size()) {
            CartProduct selectedProduct = cartItems.getCollection().get(index - 1);
            System.out.println("Enter the quantity to lower:");
            int quantity = sc.nextInt();
            sc.nextLine(); // consume the newline character
            selectedProduct.decrementQuantityInCart(quantity, cartItems);
            totalPrice -= selectedProduct.getAssosiatedProduct().getProductPrice()
                    * (1 - selectedProduct.getAssosiatedProduct().getProductDiscountPercent() / 100)
                    * quantity;
        } else {
            System.out.println("Invalid index. No product found.");
        }
    }

    private void removeProduct() {
        System.out.println("Enter the number of the product to remove first product is number 1 :");
        int index = sc.nextInt();
        sc.nextLine(); // consume the newline character

        if (index - 1 >= 0 && index - 1 < cartItems.getCollection().size()) {
            CartProduct selectedProduct = cartItems.getCollection().get(index - 1);
            totalPrice -= selectedProduct.getTotalPrice();
            selectedProduct.removeCartProduct(cartItems);

        } else {
            System.out.println("Invalid index. No product found.");
        }
    }

    public void clearCart() {
        cartItems.getCollection().clear();
        totalPrice = 0;
    }

}
