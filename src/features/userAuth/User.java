package features.userAuth;

import java.util.Scanner;

public class User {

    private static int userCounter = 0;
    private int userID;
    private String userName;
    private String userPassword;
    private int userAccessLevel; // 0 if customer , 1 if admin (used to check if an instance is a customer or an
                                 // admin to allow OR deny certain permission :D )
    private Scanner scanner;

    User(String userName, String userPassword, int userAccessLevel) {
        this.userAccessLevel = userAccessLevel;
        userID = User.userCounter;
        User.userCounter++;
        this.userPassword = userPassword;
        this.userName = userName;
        scanner = new Scanner(System.in);

    }

    // getters
    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public int getUserAccessLevel() {
        return userAccessLevel;
    }

    // setters

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserAccessLevel(int userAccessLevel) {
        this.userAccessLevel = userAccessLevel;
    }

    public void modifyUser() { // prompt the user to modify his username or password
        boolean isValidChoice = false;

        while (!isValidChoice) {
            System.out.println("Choose what to modify:");
            System.out.println("1) Change username");
            System.out.println("2) Change password");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter new username:");
                    String newUsername = scanner.nextLine();
                    setUserName(newUsername);
                    isValidChoice = true;
                    break;
                case 2:
                    System.out.println("Enter new password:");
                    String newPassword = scanner.nextLine();
                    setUserPassword(newPassword);
                    isValidChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }
}
