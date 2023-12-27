import java.util.Scanner;
import features.userAuth.User;
import features.productManagement.Product;
import features.userAuth.Auth;
import features.utilityClasses.Collection;
import features.utilityClasses.Collection;

public class App {

    public static void main(String[] args) throws Exception {
        Collection<User> usersDataBase = new Collection<User>();
        int i = -1;
        int j;
        while (i == -1) {
            j = Auth.authMenu(usersDataBase);
            System.out.println(j);
        }
    }
}
