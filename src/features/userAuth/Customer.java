package features.userAuth;

import java.util.Scanner;

import features.productManagement.Product;
import features.search.FilteredProducts;
import features.search.SearchProduct;
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
            sc.nextLine();
            if (!(choice != 1 && choice != 2 && choice != 3 && choice != 4))
                isValidChoice = true;
        }
        return choice;
    }

    public int customerShoppingPage(int userIndex, Collection<Product> products) {
        //TODO : shopping page needs implementation of dynamic search and filtering feature :(
        System.out.println(
                "Welcome to the showroom ,You can choose to search for products or apply filters for auto searching");

        boolean isValidChoice = false;
        int choice = -1;
        while (!isValidChoice) {

            System.out.println("1)Search for a specific product: ");
            System.out.println("2)Apply filters for auto-searching");
            System.out.println("3) view all products");
            System.out.println("4) go back");
            //TODO : WISHLIST (FOR GIFTING FEATURE)
            choice = sc.nextInt();
            sc.nextLine();
            if (!(choice != 1 && choice != 2 && choice != 3 && choice != 4))
                isValidChoice = true;
        }
        switch (choice) {
            case 3:
                products.listCollection();
                break;
            case 1:
                String searchInput = "";
                System.out.println("Enter the label for the product you're looking for");
                searchInput = sc.nextLine();
                Product foundProduct = SearchProduct.search(searchInput, products);
                if (foundProduct == null)
                    System.out.println("No product(s) found");
                else
                    foundProduct.productInfo();

                break;
            case 2:
                System.out.println("Choose filter options:");
                System.out.println("1) Computers only");
                System.out.println("2) Cameras only");
                System.out.println("3) Price range");
                System.out.println("4) Ratings"); // TODO: Implement filter by rating

                int filterChoice = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                Collection<Product> filteredProducts = null; // Initialize the variable

                switch (filterChoice) {
                    case 1:
                        // Filter by category (Computers only)
                        filteredProducts = FilteredProducts.filteredProducts(products, 1, 0, 0, null);
                        break;
                    case 2:
                        // Filter by category (Cameras only)
                        filteredProducts = FilteredProducts.filteredProducts(products, 2, 0, 0, null);
                        break;
                    case 3:
                        // Filter by price range
                        System.out.println("Enter minimum price:");
                        float minPrice = sc.nextFloat();
                        sc.nextLine(); // Consume the newline character

                        System.out.println("Enter maximum price:");
                        float maxPrice = sc.nextFloat();
                        sc.nextLine(); // Consume the newline character

                        filteredProducts = FilteredProducts.filteredProducts(products, 3, minPrice, maxPrice, null);
                        break;
                    case 4:
                        // TODO: Implement filter by rating
                        // filteredProducts = FilteredProducts.filteredProducts(products, 4, 0, 0, "desiredRating");
                        break;
                    default:
                        System.out.println("Invalid filter choice.");
                        break;
                }

                if (filteredProducts == null)
                    System.out.println("No product(s) found");
                else
                    filteredProducts.listCollection();

                break;
            case 4:
                System.out.println("Going back");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }

        return choice;
    }

    public int customerCartInterface(int userIndex) {
        int choice = -1;

        while (choice != 0) {
            System.out.println("Customer Cart Interface:");
            System.out.println("1. View Cart");
            System.out.println("2. Proceed to Checkout");
            System.out.println("0. Go back");

            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // View Cart
                    int cartChoice = -1;
                    while (cartChoice != 0) {
                        cartChoice = customerCart.cartMenu();
                    }
                    break;
                case 2:
                    // Proceed to Checkout
                    // Implement checkout logic here
                    System.out.println("Proceeding to Checkout...");
                    // You can add more functionality related to the checkout process
                    // For example, asking for payment information, confirming the order, etc.
                    break;
                case 0:
                    System.out.println("Going back to the previous menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        return choice;
    }

    public int customerPaymentInterface(int userIndex) {
        //todo 
        return 0;
    }

    public int customerAccountSettingsInterface(int userIndex) {
        modifyCollectable();
        return 0;
    }

    public boolean customerNavigation(int userIndex, Collection<Product> products) {
        boolean logout = false;
        boolean shouldGoBack = false;

        while (!logout) {
            int loggedInWelcomeResult = customerIsLoggedInWelcome(userIndex);
            int customerShoppingPageResult = -1;
            int customerCartInterfaceResult = -1;
            int customerPaymentInterfaceResult = -1;
            int customerAccountSettingsResult = -1;

            switch (loggedInWelcomeResult) {
                case 4:
                    // The user chose to log out
                    logout = true;
                    break;
                case 1:

                    customerShoppingPageResult = customerShoppingPage(userIndex, products);

                    break;
                case 2:
                    customerCartInterfaceResult = customerCartInterface(userIndex);
                    break;
                case 3:
                    customerAccountSettingsResult = customerAccountSettingsInterface(userIndex);
                    break;
                default:
                    break;
            }

            switch (customerShoppingPageResult) {
                case 4:
                    // Go back to the customer welcome page
                    shouldGoBack = true;
                    break;
                default:
                    break;
            }

            switch (customerCartInterfaceResult) {
                case 0:
                    // Go back to the customer welcome page
                    shouldGoBack = true;
                    break;
                default:
                    break;
            }

            switch (customerPaymentInterfaceResult) {
                // Implement cases for payment interface as needed
                default:
                    break;
            }

            // Add more switch statements or conditions for other sections as needed.

            // Break out of the loop if shouldGoBack is true

        }
        return logout;
    }

}
