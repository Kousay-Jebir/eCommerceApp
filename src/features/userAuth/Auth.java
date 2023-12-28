package features.userAuth;

import features.utilityClasses.Collection;

import java.util.Scanner;

public class Auth {
    public static int findUser(Collection<User> users, String username, String password) {
        int index = -1;
        int i = 0;
        while (i < users.getCollection().size()) {
            if (users.getCollection().get(i).getUserName().equals(username)) {
                break; // Exit loop if username is found
            }
            i++;
        }

        if (i == users.getCollection().size()) {
            System.out.println("Account with such username doesn't exist");
            return index;
        }

        if (!users.getCollection().get(i).getUserPassword().equals(password)) {
            System.out.println("Incorrect password");
            return index;
        }
        return i;
    } // returns index of the user we are trying to log in to

    // Checks if a given username is unique in the list of users
    private static boolean isUsernameUnique(Collection<User> users, String username) {
        for (User user : users.getCollection()) {
            if (user.getUserName().equals(username)) {
                return false; // Username already exists
            }
        }
        return true; // Username is unique
    }

    // Common method for creating a new user (admin or customer)
    private static int createUser(Collection<User> users, String userType) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String userName = sc.nextLine();

        if (isUsernameUnique(users, userName)) {
            System.out.println("Enter password: ");
            String password = sc.nextLine();

            User newUser = null;
            if (userType.equals("Admin")) {
                System.out.println("Enter working category: ");
                String category = sc.nextLine();
                newUser = new Admin(userName, password, 1, category);
            } else if (userType.equals("Customer")) {
                newUser = new Customer(userName, password, 0);
            }

            users.addCollectable(newUser);
            System.out.println("Account made successfully, you will be logged in");
            return users.getCollection().size() - 1;
        } else {
            System.out.println("Username already exists. Please choose a different username.");
            return -1; // Indicate failure
        }
    }

    // Main method for user authentication menu
    public static int authMenu(Collection<User> users) {
        Scanner sc = new Scanner(System.in);
        boolean isValidChoice = false;
        int choice = -1;

        while (!isValidChoice) {
            System.out.println(
                    "Type '1' if you wish to Make a new account\nType '2' if you wish to LogIn to an existing account ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    int accountTypeChoice;
                    while (true) {
                        System.out.println(
                                "Type '1' if you wish to make an admin account\nType '2' if you wish to make a customer account ");
                        accountTypeChoice = sc.nextInt();
                        sc.nextLine();
                        if (accountTypeChoice == 1 || accountTypeChoice == 2) {
                            break;
                        }
                        System.out.println("Invalid choice. Try again");
                    }

                    String userType = (accountTypeChoice == 1) ? "Admin" : "Customer";
                    return createUser(users, userType);

                case 2:
                    System.out.println("Enter username: ");
                    String userName = sc.nextLine();
                    System.out.println("Enter password: ");
                    String password = sc.nextLine();
                    isValidChoice = true;
                    return findUser(users, userName, password);

                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }

        sc.close(); // Close the Scanner
        return choice;
    }
}
