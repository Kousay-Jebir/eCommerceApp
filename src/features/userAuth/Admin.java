package features.userAuth;

import features.productManagement.Camera;
import features.productManagement.Computer;
import features.productManagement.Product;
import features.utilityClasses.Collection;
import java.util.Scanner;

public class Admin extends User {
    private Scanner sc = new Scanner(System.in);
    private String worksOnCategory; // specifies which category the admin works on
    // can be either PC , CAM or USER

    public Admin(String userName, String userPassword, int userAccessLevel, String category) {
        super(userName, userPassword, userAccessLevel);
        worksOnCategory = category;

    }
    //TODO : ADMIN MENU

    public int adminLoggedInWelcome(int userIndex) {
        //this section is a welcoming page where the admin can choose to log out or access his dashboard
        System.out.println("Welcome back Admin " + this.getUserName() + " " + this.getUserID());
        boolean isValidChoice = false;
        int choice = -1;
        while (!isValidChoice) {

            System.out.println("1) Access your dashboard");
            System.out.println("2) Log out");
            choice = sc.nextInt();
            if (choice != 1 && choice != 2)
                System.out.println("invalid choice");

            else {
                break;
            }
        }
        return choice;
    }

    public int adminDashBoardWelcome(int userIndex) {

        System.out.println("Admin " + this.getUserID() + " Dashboard section");

        int choice = -1;
        while (true) {
            System.out.println("1) Access the moderation section");
            System.out.println("2) Access the product management section");
            choice = sc.nextInt();
            if (choice != 1 && choice != 2)
                System.out.println("invalid choice");

            else {
                break;
            }
        }
        return choice;

        //Moderation section
        //This section is reserved for banning users (completely removing their account)
        // and checking out the users database

        //Product management section
        //This section is reserved for product management (Admins perform crud operations on products under the specific category they are working on)

        //
    }

    public int adminDashBoardModeration(int userIndex, Collection<User> users) {

        System.out.println("Admin " + this.getUserID() + " Dashboard/Moderation section");
        boolean isValidChoice = false;
        int choice = -1;
        while (!isValidChoice) {
            System.out.println("1) List all users in the database");
            System.out.println("2) Ban a user");
            System.out.println("3) go back");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    users.listCollection();
                    isValidChoice = true;
                    break;
                case 2:
                    System.out.println("Type User's ID to ban");
                    users.removeCollectable(users.getCollection().get(users.findUserById(sc.nextInt())));
                    isValidChoice = true;
                    break;
                case 3:
                    isValidChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        return choice;

        //Moderation section
        //This section is reserved for banning users (completely removing their account)
        // and checking out the users database

        //Product management section
        //This section is reserved for product management (Admins perform crud operations on products under the specific category they are working on)

        //
    }

    public int adminDashBoardProductManagement(int userIndex, Collection<Product> products) {
        System.out.println("Admin " + this.getUserID() + " Dashboard/ProductManagement section");
        boolean isValidChoice = false;
        int choice = -1;
        while (!isValidChoice) {
            System.out.println("1) List all products in the database");
            System.out.println("2) Remove a product");
            System.out.println("3) Add a new product");
            System.out.println("4) Modify a product");
            System.out.println("5) Go back");
            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    products.listCollection();
                    isValidChoice = true;
                    break;
                case 2:
                    System.out.println("Type Product's ID to remove");
                    int productIdToRemove = sc.nextInt();
                    sc.nextLine(); // Consume the newline character
                    products.removeCollectable(
                            products.getCollection().get(products.findProductById(productIdToRemove)));
                    isValidChoice = true;
                    break;
                case 3:
                    System.out.println("Adding a new product:");

                    System.out.print("Enter product label: ");
                    String label = sc.nextLine();

                    System.out.print("Enter product description: ");
                    String description = sc.nextLine();

                    System.out.print("Enter discount percentage: ");
                    float discount = sc.nextFloat();
                    sc.nextLine(); // Consume the newline character

                    System.out.print("Enter quantity in stock: ");
                    int quantity = sc.nextInt();
                    sc.nextLine(); // Consume the newline character

                    System.out.print("Enter price: ");
                    float price = sc.nextFloat();
                    sc.nextLine(); // Consume the newline character

                    System.out.print("Enter product category (PC or CAM): ");
                    String category = sc.nextLine();

                    Product newProduct;

                    if (category.equalsIgnoreCase("PC")) {
                        System.out.print("Enter CPU: ");
                        String cpu = sc.nextLine();

                        System.out.print("Enter GPU: ");
                        String gpu = sc.nextLine();

                        System.out.print("Enter RAM: ");
                        String ram = sc.nextLine();

                        newProduct = new Computer(label, description, discount, quantity, price, cpu, gpu, ram);
                    } else if (category.equalsIgnoreCase("CAM")) {
                        System.out.print("Enter camera brand: ");
                        String brand = sc.nextLine();

                        System.out.print("Enter camera lens: ");
                        String lens = sc.nextLine();

                        newProduct = new Camera(label, description, discount, quantity, price, brand, lens);
                    } else {
                        System.out.println("Invalid category. Please choose either PC or CAM.");
                        break;
                    }

                    products.addCollectable(newProduct);
                    System.out.println("Product added successfully!");
                    isValidChoice = true;
                    break;
                case 4:
                    System.out.println("Enter product ID to modify");
                    int productIdToModify = sc.nextInt();
                    sc.nextLine(); // Consume the newline character
                    products.getCollection().get(products.findProductById(productIdToModify)).modifyCollectable();
                    isValidChoice = true;
                    break;
                case 5:
                    isValidChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        return choice;
    }

    // This navigation method is used to link between the menus 
    public boolean adminNavigation(int userIndex, Collection<User> users, Collection<Product> products) {
        boolean logout = false;
        boolean shouldGoBack = false;

        while (!logout /*&& !shouldGoBack*/) {
            int loggedInWelcomeResult = adminLoggedInWelcome(userIndex);
            int adminDashBoardResult = -1;
            int adminDashBoardModerationResult = -1;
            int adminDashboardProductManResult = -1;

            switch (loggedInWelcomeResult) {
                case 2:
                    // The user chose to log out
                    logout = true;
                    break;
                case 1:
                    adminDashBoardResult = adminDashBoardWelcome(userIndex);
                    break;
                default:
                    break;
            }

            switch (adminDashBoardResult) {
                case 1:
                    adminDashBoardModerationResult = adminDashBoardModeration(userIndex, users);
                    break;
                case 2:
                    adminDashboardProductManResult = adminDashBoardProductManagement(userIndex, products);
                    break;
                default:
                    break;
            }

            switch (adminDashBoardModerationResult) {
                case 3:
                    // Go back to the admindashboard welcome management section
                    shouldGoBack = true;
                    break;
                default:
                    break;
            }

            switch (adminDashboardProductManResult) {
                case 5:
                    //goes back to home page
                    shouldGoBack = true;
                    break;
                default:
                    break;
            }

            // Add more switch statements or conditions for other sections as needed.

        }
        return logout;
    }

}
