package features.productManagement;

import java.util.Scanner;

import interfaces.Collectable;

public abstract class Product implements Collectable {
    private Scanner scanner = new Scanner(System.in);
    private static int productCounter = 0;
    private int productID;
    protected String productLabel;
    protected String productDesc; // a brief description of the product

    protected float productDiscountPercent;
    protected int productQuantity;
    protected float productPrice;
    // TODO : RATING (how many starts ?)
    // TODO : GIFTING MECHANISM

    public Product(String label, String desc, float discount, int quantity, float price) {
        productID = Product.productCounter;
        Product.productCounter++;
        productLabel = label;
        productDesc = desc;

        productDiscountPercent = discount;
        productQuantity = quantity;
        productPrice = price;
    }

    // getters and setters
    public String getProductLabel() {
        return productLabel;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public float getProductDiscountPercent() {
        return productDiscountPercent;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductLabel(String label) {
        productLabel = label;

    }

    public void setProductDesc(String desc) {

        productDesc = desc;

    }

    public void setProductDiscountPercent(float discount) {

        productDiscountPercent = discount;

    }

    public void setProductQuantity(int quan) {

        productQuantity = quan;

    }

    public void setProductPrice(float price) {

        productPrice = price;
    }

    // Methods
    public abstract String productCategory();

    public void productInfo() { // Polymorph method to redefine in subclasses
        System.out.println("Name : " + productLabel);
        System.out.println("Item description : " + productDesc);
        System.out.println("Quantity in stock : " + productQuantity);

        if (productDiscountPercent != 0) {
            System.out.println("Discount percentage : " + productDiscountPercent);
            System.out.println("Original price: " + productPrice);
            System.out.println("Price after reduction : " + (productPrice * (1 - productDiscountPercent / 100)));
        } else {
            System.out.println("Price : " + productPrice);
        }

    }

    public void modifyCollectable() {
        boolean isValidChoice = false;

        while (!isValidChoice) {
            System.out.println("Choose what to modify:");
            System.out.println("1) Change price");
            System.out.println("2) Change quantity");
            System.out.println("3) Change description");
            System.out.println("4) Change discount percentage");
            System.out.println("5) Change label");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter new price:");
                    float newPrice = scanner.nextFloat();
                    setProductPrice(newPrice);
                    isValidChoice = true;
                    break;
                case 2:
                    System.out.println("Enter new quantity:");
                    int newQuantity = scanner.nextInt();
                    setProductQuantity(newQuantity);
                    isValidChoice = true;
                    break;
                case 3:
                    System.out.println("Enter new description:");
                    String newDesc = scanner.nextLine();
                    setProductDesc(newDesc);
                    isValidChoice = true;
                    break;
                case 4:
                    System.out.println("Enter new percentage:");
                    float newPercentage = scanner.nextFloat();
                    setProductDiscountPercent(newPercentage);
                    isValidChoice = true;
                    break;
                case 5:
                    System.out.println("Enter new label:");
                    String newLabel = scanner.nextLine();
                    setProductLabel(newLabel);
                    isValidChoice = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }

    public String toString() {
        productInfo();
        return "";
    }
}
