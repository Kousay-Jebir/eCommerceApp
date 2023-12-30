package features.orderProcessing;

import features.shoppingCart.Cart;
import features.shoppingCart.CartProduct;
import features.trasnactionHistory.Order;
import features.userAuth.Customer;

import java.util.Scanner;

public class PaymentCheckout {
    // give a user's a cart and his action to proceed to payment this class is a collection of methods that simulate payment validation / denial 
    //a payment validation is followed up by an automatic inventory update 
    // handling scnearios where a user is trying to buy an item with a quantity > stock quantity
    private static Scanner sc = new Scanner(System.in);

    public static void paymentMenu(Cart userCart, Customer customer) {
        System.out.println("Proceed to checkout? (yes/no)");
        String userChoice = sc.nextLine();

        if (userChoice.equalsIgnoreCase("yes")) {
            // Simulate payment validation
            boolean paymentSuccess = simulatePaymentValidation(userCart);

            if (paymentSuccess) {

                System.out.println("Payment successful! Thank you for your purchase.");
                //save the order in the customer's transactions history 
                customer.getCustomerTransactionHistory().addCollectable(
                        new Order(userCart.getCartItems(), userCart.getTotalPrice(), customer.getUserName()));
                //Clear customer's cart
                userCart.clearCart();
            } else {
                System.out.println("Payment was not successful! Sorry for the inconvenience.");
            }
        } else {
            System.out.println("Transaction canceled. Returning to the main menu.");
        }
    }

    private static boolean simulatePaymentValidation(Cart useCart) {
        int quantityInCart;
        int quantityInStock;
        for (CartProduct cartProduct : useCart.getCartItems().getCollection()) {
            quantityInCart = cartProduct.getQuantityInCart();
            quantityInStock = cartProduct.getAssosiatedProduct().getProductQuantity();
            if (quantityInCart > quantityInStock) {
                System.out.println("Quantity in stock for " + cartProduct.getAssosiatedProduct().getProductLabel()
                        + " is not sufficient");
                return false;
            } else {
                cartProduct.getAssosiatedProduct().setProductQuantity(quantityInStock - quantityInCart);
            }
        }

        return true;

    }

}
