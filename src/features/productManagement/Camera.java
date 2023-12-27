package features.productManagement;

public class Camera extends Product {

    private String cameraBrand;
    private String cameraLens;

    public Camera(String label, String desc, float discount, int quantity, float price, String brand, String lens) {
        super(label, desc, discount, quantity, price);
        cameraBrand = brand;
        cameraLens = lens;
    }

    public String getCameraBrand() {
        return cameraBrand;
    }

    public String getCameraLens() {
        return cameraLens;
    }

    public void setCameraBrand(String brand) {
        cameraBrand = brand;
    }

    public void setCameraLens(String lens) {
        cameraLens = lens;
    }

    @Override
    public String productCategory() {
        System.out.println("Category: Camera");
        return "CAM";

    }

    @Override
    public void productInfo() {
        productCategory();
        super.productInfo();
    }
}
