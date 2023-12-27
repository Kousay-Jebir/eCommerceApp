package features.userAuth;

public class Admin extends User {
    private String worksOnCategory; // specifies which category the admin works on
    // can be either PC , CAM or USER

    public Admin(String userName, String userPassword, int userAccessLevel, String category) {
        super(userName, userPassword, userAccessLevel);
        worksOnCategory = category;

    }
    //TODO : ADMIN MENU
}
