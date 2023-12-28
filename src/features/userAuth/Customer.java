package features.userAuth;

import java.util.Scanner;

import features.productManagement.Product;
import features.shoppingCart.Cart;
import features.utilityClasses.Collection;

public class Customer extends User {

    private Scanner sc = new Scanner(System.in);
    private final Cart customerCart;

    public Customer(String userName, String userPassword, int userAccessLevel) {
        super(userName, userPassword, userAccessLevel);
        customerCart = new Cart();
    }

    //TODO : Customer menu
    public int customerIsLoggedInWelcome(int userIndex) {
        //welcome page for customer to access other pages or to log out 
        //in the diagrma provided in github every page goes back to the welcome page 
        // for simplicity reasons i didn't implement a more complex approach where pages are linked together , instead most of the menus are linked together throught the welcome page
        // althought this affects User experience , it's okey haha
        System.out.println("Welcome back Customer " + this.getUserName() + " " + this.getUserID());
        boolean isValidChoice = false;
        int choice = -1;
        while (!isValidChoice) {

            System.out.println("1) Shopping page");
            System.out.println("2)Your cart");
            System.out.println("3) Account settings");
            System.out.println("4) Log out");
            //TODO : WISHLIST (FOR GIFTING FEATURE)
            choice = sc.nextInt();

        }
        return choice;
    }

    public int customerShoppingPage(int userIndex, Collection<Product> products) {
        //TODO : shopping page needs implementation of dynamic search and filtering feature :(
        return 0;
    }

    public int customerCartInterface(int userIndex/* , cart...*/) {
        return 0;
    }
}
