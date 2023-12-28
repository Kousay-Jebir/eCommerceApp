package features.shoppingCart;

import java.util.Scanner;

import features.productManagement.Product;
import features.userAuth.Customer;
import features.utilityClasses.Collection;

public class Cart {
    private Scanner sc = new Scanner(System.in);
    private Collection<CartProduct> cartItems = new Collection<CartProduct>();
    private float totalPrice = 0;

    public void addToCart(Product product, int quantity) {
        //extract a CartProduct from the given product 
        CartProduct productToAdd = new CartProduct(product, quantity);
        cartItems.addCollectable(productToAdd);
        totalPrice = totalPrice + productToAdd.getTotalPrice();
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
            selectedProduct.incrementQuantityInCart(quantity);
            totalPrice += selectedProduct.getAssosiatedProduct().getProductPrice()
                    * (1 - selectedProduct.getAssosiatedProduct().getProductDiscountPercent())
                    * quantity;
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
                    * (1 - selectedProduct.getAssosiatedProduct().getProductDiscountPercent())
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

}
