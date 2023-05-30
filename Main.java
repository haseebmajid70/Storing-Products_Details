import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String itemName;
    private String companyName;
    private String modelNumber;
    private double price;

    public Product(String itemName, String companyName, String modelNumber, double price) {
        this.itemName = itemName;
        this.companyName = companyName;
        this.modelNumber = modelNumber;
        this.price = price;
    }

    // Getters and setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Override toString() method for displaying the product information
    @Override
    public String toString() {
        return "Item Name: " + itemName + ", Company: " + companyName +
                ", Model Number: " + modelNumber + ", Price: " + price;
    }
}

public class Main  {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();

        System.out.print("Enter the number of products: ");
        int numProducts = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline character

        for (int i = 0; i < numProducts; i++) {
            System.out.println("Enter details for Product " + (i + 1) + ":");
            Product product = createProduct(scanner);
            products.add(product);
        }

        // Display the products
        System.out.println("\nProducts in the shop:");
        for (Product product : products) {
            System.out.println(product);
        }

        // Store the products in a text file on the desktop
        String desktopPath = System.getProperty("user.home") + "/Desktop/";
        String fileName = "stock.txt";
        String filePath = desktopPath + fileName;
        storeProductsToFile(products, filePath);

        System.out.println("\nProduct details have been stored in the file: " + filePath);
    }

    private static Product createProduct(Scanner scanner) {
        System.out.print("Enter Item Name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter Company Name: ");
        String companyName = scanner.nextLine();
        System.out.print("Enter Model Number: ");
        String modelNumber = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the remaining newline character

        return new Product(itemName, companyName, modelNumber, price);
    }

    private static void storeProductsToFile(List<Product> products, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Product product : products) {
                writer.println(
                        product.getItemName() + "," +
                                product.getCompanyName() + "," +
                                product.getModelNumber() + "," +
                                product.getPrice()
                );
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}