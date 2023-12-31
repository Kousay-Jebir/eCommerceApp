package features.orderProcessing;

import features.payment.Payment;
import features.shoppingCart.Cart;
import features.shoppingCart.CartProduct;
import features.trasnactionHistory.Order;
import features.userAuth.Customer;
import features.utilityClasses.CustomRandomGenerator;

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

                //Payment interface

                cartProduct.getAssosiatedProduct().setProductQuantity(quantityInStock - quantityInCart);
            }

        }

        //Payment interface
        Payment.collectPaymentInformation();

        //Simulate if user has enough credid in his credit card to pay the fees
        float totalToPay = useCart.getTotalPrice();
        float[] numbers = { totalToPay, 0f };
        float[] probabilities = { 0.5f, 0.5f };
        float generatedNumber = CustomRandomGenerator.generateNumber(numbers, probabilities);
        if (generatedNumber == 0) {
            System.out.println("Not enough credit in your cc maybe try generating a promotion discount :D");
            return false;
        }

        return true;

    }

}
