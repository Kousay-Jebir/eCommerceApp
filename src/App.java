import java.util.Scanner;

import features.userAuth.Auth;
import features.userAuth.Users;

public class App {

    public static void main(String[] args) throws Exception {
        Users usersDataBase = new Users();
        int i = -1;
        int j;
        while (i == -1) {
            j = Auth.authMenu(usersDataBase);
            System.out.println(j);
        }
    }
}
