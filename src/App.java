import java.util.Scanner;
import features.userAuth.User;
import features.productManagement.Product;
import features.userAuth.Auth;
import features.userAuth.Customer;
import features.utilityClasses.Collection;
import features.userAuth.Admin;

public class App {
    public static void main(String[] args) {
        int authResult;
        boolean isLoggedOut = false;
        Collection<User> usersDataBase = new Collection<User>();
        Collection<Product> productsDataBase = new Collection<Product>();
        while (true) {
            isLoggedOut = false;

            while (!isLoggedOut) {
                try {
                    authResult = Auth.authMenu(usersDataBase);
                    User loggedInUser = usersDataBase.getCollection().get(authResult);
                    if (loggedInUser.getUserAccessLevel() == 1) {
                        isLoggedOut = ((Admin) loggedInUser).adminNavigation(authResult, usersDataBase,
                                productsDataBase);
                    } else {
                        isLoggedOut = ((Customer) loggedInUser).customerNavigation(authResult,
                                productsDataBase);
                    }
                } catch (Exception e) {
                    // Handle the exception (e.g., print an error message)
                    System.out.println("Error during authentication: ");
                }
            }
        }
    }
}
