package features.userRating;

import features.userAuth.Customer;

public class Rating {
    private int numberOfStars;
    private String userFeedback;
    private final Customer assosiatedCustomer;

    public Rating(int numberOfStars, String userFeedback, Customer assosiatedCustomer) {
        this.numberOfStars = numberOfStars;
        this.userFeedback = userFeedback;
        this.assosiatedCustomer = assosiatedCustomer;
    }

    public void ratingMention() {
        switch (numberOfStars) {
            case 1:
                System.out.println(numberOfStars + " star(s) (very insatisfied)");
                break;
            case 2:
                System.out.println(numberOfStars + " star(s) (insatisfied)");
                break;
            case 3:
                System.out.println(numberOfStars + " star(s) (satisfied)");
                break;
            case 4:
                System.out.println(numberOfStars + " star(s) (very satisfied)");
            default:
                break;
        }
    }

    public String toString() {
        System.out.println("&_______________________________&");
        System.out.println(assosiatedCustomer.getUserName());
        ratingMention();
        System.out.println("-----------------------");
        System.out.println(userFeedback);
        System.out.println("-----------------------");

        return "";
    }
}
