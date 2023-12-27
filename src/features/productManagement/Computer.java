package features.productManagement;

public class Computer extends Product {
    // some parts for the computer
    private String computerCPU;
    private String computerGPU;
    private String computerRAM;

    public Computer(String label, String desc, float discount, int quantity, float price, String cpu, String gpu,
            String ram) {
        super(label, desc, discount, quantity, price);
        computerCPU = cpu;
        computerGPU = gpu;
        computerRAM = ram;
    }

    public String getComputerCPU() {
        return computerCPU;
    }

    public String getComputerGPU() {
        return computerGPU;
    }

    public String getComputerRAM() {
        return computerRAM;
    }

    public void setComputerCPU(String cpu) {
        computerCPU = cpu;
    }

    public void setComputerGPU(String gpu) {
        computerGPU = gpu;
    }

    public void setComputerRAM(String ram) {
        computerRAM = ram;
    }

    @Override
    public String productCategory() {
        System.out.println("Category: Computer");
        return "PC";

    }

    @Override
    public void productInfo() {
        productCategory();
        super.productInfo();
    }

}
