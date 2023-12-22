package features.userAuth;

import java.util.ArrayList;

public class Users {
    private ArrayList<User> usersList; // Vector that sotres all the users

    public Users() {
        usersList = new ArrayList<User>();
    }

    public ArrayList<User> getUsers() {
        return usersList;
    }

    public void addUser(User user) {
        usersList.add(user);
    }

    public void removeUser(User user) {
        usersList.remove(user);
    }
}
