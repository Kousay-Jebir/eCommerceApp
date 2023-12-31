package features.payment;

import features.shoppingCart.Cart;

import java.util.Scanner;

public class Payment {

    private static Scanner sc = new Scanner(System.in);

    public static boolean processPayment(Cart userCart) {
        // Placeholder implementation: Assume payment is always successful

        // Collect payment information
        collectPaymentInformation();

        // Simulate payment processing
        return true;
    }

    public static void collectPaymentInformation() {
        System.out.println("Enter credit card information:");
        System.out.print("Card Number: ");
        String cardNumber = sc.nextLine();
        System.out.print("Expiration Date: ");
        String expirationDate = sc.nextLine();
        System.out.print("CVV: ");
        String cvv = sc.nextLine();

        // Placeholder: Validate the payment information (in a real system, you'd perform actual validation)
        if (isValidPaymentInformation(cardNumber, expirationDate, cvv)) {
            System.out.println("Payment information is valid.");
        } else {
            System.out.println("Invalid payment information. Payment failed.");
        }
    }

    private static boolean isValidPaymentInformation(String cardNumber, String expirationDate, String cvv) {
        // Placeholder: Perform actual validation (e.g., check against a database)
        return !cardNumber.isEmpty() && !expirationDate.isEmpty() && !cvv.isEmpty();
    }
}
