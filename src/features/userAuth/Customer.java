package features.userAuth;

import java.util.Scanner;

import features.orderProcessing.PaymentCheckout;
import features.productManagement.Product;
import features.search.FilteredProducts;
import features.search.SearchProduct;
import features.shoppingCart.Cart;
import features.shoppingCart.CartProduct;
import features.trasnactionHistory.Order;
import features.userRating.Rating;
import features.utilityClasses.Collection;

public class Customer extends User {

    private Scanner sc = new Scanner(System.in);
    private final Cart customerCart;
    private final Collection<Order> customerTransactionHistory;

    public Collection<Order> getCustomerTransactionHistory() {
        return customerTransactionHistory;
    }

    // Add this method to the Customer class
    public void applyDiscount(float discountPercentage) {
        if (discountPercentage >= 0 && discountPercentage <= 100) {
            float discountFactor = 1 - (discountPercentage / 100);
            float discountedTotal = customerCart.getTotalPrice() * discountFactor;
            customerCart.setTotalPrice(discountedTotal);
            System.out.println("Discount of " + discountPercentage + "% applied successfully!");
            System.out.println("Updated Cart Total: $" + discountedTotal);
        } else {
            System.out.println("Invalid discount percentage. Please provide a value between 0 and 100.");
        }
    }

    public void viewTransactionHistory() {
        System.out.println("Transaction History:");

        for (Order order : customerTransactionHistory.getCollection()) {
            System.out.println(order);
            System.out.println("-----------------------------");

            // Ask the user if they want to leave a rating for each product in the order
            for (CartProduct cartProduct : order.getOrder().getCollection()) {
                Product purchasedProduct = cartProduct.getAssosiatedProduct();
                System.out.println(
                        "Do you want to leave a rating for " + purchasedProduct.getProductLabel() + "? (yes/no)");
                String leaveRatingChoice = sc.nextLine();

                if (leaveRatingChoice.equalsIgnoreCase("yes")) {
                    System.out.println("Enter your rating (1-4):");
                    int ratingValue = sc.nextInt();
                    sc.nextLine(); // Consume the newline character

                    // Validate the rating value (assuming it should be between 1 and 4)
                    if (ratingValue >= 1 && ratingValue <= 4) {
                        System.out.println("Enter your feedback:");
                        String feedback = sc.nextLine();
                        // Create a new Rating object and add it to the product's ratings
                        Rating newRating = new Rating(ratingValue, feedback, this);
                        purchasedProduct.getCustomerRatings().addCollectable(newRating);
                        purchasedProduct.calculateAverageOfStars();
                        System.out.println("Thank you for your rating!");
                    } else {
                        System.out.println("Invalid rating value. Rating should be between 1 and 4.");
                    }
                }
            }
        }
    }

    public Customer(String userName, String userPassword, int userAccessLevel) {
        super(userName, userPassword, userAccessLevel);
        customerCart = new Cart();
        customerTransactionHistory = new Collection<Order>();
    }

    //TODO : Customer menu

    public int viewProductRatingsInterface(Collection<Product> products) {
        while (true) {
            System.out.println("Enter the number of the product you want to view ratings for enter -1 to exit:");
            int productNumber = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            if (productNumber > 0 && productNumber <= products.getCollection().size()) {
                Product selectedProduct = products.getCollection().get(productNumber - 1);
                selectedProduct.getCustomerRatings().listCollection();
            } else if (productNumber == -1)
                return 0;
        }

    }

    public int viewProductRatingsInterface(Product product) {
        while (true) {
            System.out.println("Enter the number of the product you want to view ratings for enter -1 to exit:");
            int productNumber = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            if (productNumber == 1) {
                product.getCustomerRatings().listCollection();
            } else if (productNumber == -1)
                return 0;
        }

    }

    public int addToCartInterface(Collection<Product> products) {
        System.out.println("Adding Items to Cart Interface");
        System.out.println("Choose an action:");
        System.out.println("1. Add a product to your cart");
        System.out.println("0. Go back");

        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                System.out.println("Enter the number of the product you want to add to your cart:");
                int productNumber = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                if (productNumber > 0 && productNumber <= products.getCollection().size()) {
                    Product selectedProduct = products.getCollection().get(productNumber - 1);

                    System.out.println("Enter the quantity you want to add:");
                    int quantity = sc.nextInt();
                    sc.nextLine(); // Consume the newline character

                    // Check if the requested quantity is available in stock
                    if (quantity <= selectedProduct.getProductQuantity()) {
                        // Prompt user if they want to add the product to the cart
                        System.out.println("Do you want to add " + quantity + " " + selectedProduct.getProductLabel()
                                + "(s) to your cart? (yes/no)");
                        String addToCartChoice = sc.nextLine();

                        if (addToCartChoice.equalsIgnoreCase("yes")) {
                            customerCart.addToCart(selectedProduct, quantity);
                            System.out.println("Product added to cart successfully!");
                        } else {
                            System.out.println("Product not added to cart.");
                        }
                    } else {
                        System.out.println("Not enough quantity in stock. Please choose a smaller quantity.");
                    }
                } else {
                    System.out.println("Invalid product number. Please try again.");
                }
                break;
            case 0:
                System.out.println("Going back to the previous menu.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        return choice;
    }

    public int addToCartInterface(Product product) { //overloading this method
        System.out.println("Adding Items to Cart Interface");
        System.out.println("Choose an action:");
        System.out.println("1. Add a product to your cart");
        System.out.println("0. Go back");

        int choice = sc.nextInt();
        sc.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                System.out.println("Enter the number of the product you want to add to your cart:");
                int productNumber = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                if (true) {
                    Product selectedProduct = product;

                    System.out.println("Enter the quantity you want to add:");
                    int quantity = sc.nextInt();
                    sc.nextLine(); // Consume the newline character

                    // Check if the requested quantity is available in stock
                    if (quantity <= selectedProduct.getProductQuantity()) {
                        // Prompt user if they want to add the product to the cart
                        System.out.println("Do you want to add " + quantity + " " + selectedProduct.getProductLabel()
                                + "(s) to your cart? (yes/no)");
                        String addToCartChoice = sc.nextLine();

                        if (addToCartChoice.equalsIgnoreCase("yes")) {
                            customerCart.addToCart(selectedProduct, quantity);
                            System.out.println("Product added to cart successfully!");
                        } else {
                            System.out.println("Product not added to cart.");
                        }
                    } else {
                        System.out.println("Not enough quantity in stock. Please choose a smaller quantity.");
                    }
                } else {
                    System.out.println("Invalid product number. Please try again.");
                }
                break;
            case 0:
                System.out.println("Going back to the previous menu.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        return choice;
    }

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
            System.out.println("5) View transaction history");
            System.out.println("6) apply a discount");
            //TODO : WISHLIST (FOR GIFTING FEATURE)
            choice = sc.nextInt();
            sc.nextLine();
            if (!(choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5))
                isValidChoice = true;
            if (choice == 6) {
                System.out.println("Enter the discount percentage:");
                float discountPercentage = sc.nextFloat();
                sc.nextLine(); // Consume the newline character
                applyDiscount(discountPercentage);
                isValidChoice = true;
            }
        }
        return choice;
    }

    public int customerShoppingPage(int userIndex, Collection<Product> products) {
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
        int promptToAddToCart;
        int promptToviewProductRatings;
        switch (choice) {

            case 3:
                products.listCollection();
                promptToviewProductRatings = viewProductRatingsInterface(products);
                promptToAddToCart = addToCartInterface(products);

                break;
            case 1:
                String searchInput = "";
                System.out.println("Enter the label for the product you're looking for");
                searchInput = sc.nextLine();
                Product foundProduct = SearchProduct.search(searchInput, products);
                if (foundProduct == null)
                    System.out.println("No product(s) found");
                else {
                    foundProduct.productInfo();

                    promptToviewProductRatings = viewProductRatingsInterface(foundProduct);
                    promptToAddToCart = addToCartInterface(foundProduct);
                }
                break;
            case 2:
                System.out.println("Choose filter options:");
                System.out.println("1) Computers only");
                System.out.println("2) Cameras only");
                System.out.println("3) Price range");
                System.out.println("4) Ratings");

                int filterChoice = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                Collection<Product> filteredProducts = null; // Initialize the variable

                switch (filterChoice) {
                    case 1:
                        // Filter by category (Computers only)
                        filteredProducts = FilteredProducts.filteredProducts(products, 1, 0, 0, 0, 0);
                        break;
                    case 2:
                        // Filter by category (Cameras only)
                        filteredProducts = FilteredProducts.filteredProducts(products, 2, 0, 0, 0, 0);
                        break;
                    case 3:
                        // Filter by price range
                        System.out.println("Enter minimum price:");
                        float minPrice = sc.nextFloat();
                        sc.nextLine(); // Consume the newline character

                        System.out.println("Enter maximum price:");
                        float maxPrice = sc.nextFloat();
                        sc.nextLine(); // Consume the newline character

                        filteredProducts = FilteredProducts.filteredProducts(products, 3, minPrice, maxPrice, 0, 0);
                        // int doesUserAddToCartResult = addToCartInterface(filteredProducts);
                        break;
                    case 4:
                        // Filter by ratings
                        System.out.println("Enter minimum stars average:");
                        float minStars = sc.nextFloat();
                        sc.nextLine(); // Consume the newline character

                        System.out.println("Enter maximum stars average:");
                        float maxStars = sc.nextFloat();
                        sc.nextLine(); // Consume the newline character

                        filteredProducts = FilteredProducts.filteredProducts(products, 4, 0, 0, minStars, maxStars);
                        // int doesUserAddToCartResult = addToCartInterface(filteredProducts);
                        break;
                    default:
                        System.out.println("Invalid filter choice.");
                        break;
                }

                if (filteredProducts == null)
                    System.out.println("No product(s) found");
                else {
                    filteredProducts.listCollection();

                    promptToviewProductRatings = viewProductRatingsInterface(filteredProducts);
                    promptToAddToCart = addToCartInterface(filteredProducts);
                }
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
                    PaymentCheckout.paymentMenu(customerCart, this);
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
                case 5:
                    viewTransactionHistory();
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
