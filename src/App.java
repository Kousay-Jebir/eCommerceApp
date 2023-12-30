import java.util.Scanner;
import features.userAuth.User;
import features.productManagement.Camera;
import features.productManagement.Computer;
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
        //adding dummy products 
        productsDataBase
                .addCollectable(new Computer("asus-124", "dummu description", 25, 10, 1500, "i5", "gtx1050", "16gb"));
        productsDataBase
                .addCollectable(new Computer("lenovo-FF16", "dummu description", 50, 25, 2000, "i7", "gtx1070", "8gb"));
        productsDataBase
                .addCollectable(new Computer("acer-210", "dummu description", 0, 5, 3200, "i9", "rtx4060", "16gb"));
        productsDataBase
                .addCollectable(new Computer("asus-125", "dummu description", 0, 20, 1820, "i5", "gtx", "16gb"));
        productsDataBase.addCollectable(new Camera("canon-911", "dummu description", 25, 10, 4700, "Canon", "x9mm"));
        productsDataBase.addCollectable(new Camera("nikon-911", "dummu description", 0, 10, 4700, "Nikon", "x1.5mm"));
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
