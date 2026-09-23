package inventorystockmonitor;

import java.util.Scanner;

public class InventoryStockMonitor {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int productNum;

        do {
            System.out.print("Enter number of products (1-8): ");
            productNum = input.nextInt();

            if (productNum < 1 || productNum > 8) {
                System.out.println("Please enter from 1 to 8 only.");
            }
        } while (productNum < 1 || productNum > 8);

        Product[] products = new Product[productNum];
        input.nextLine();

        for (int i = 0; i < productNum; i++) {
            System.out.println("\nProduct " + (i + 1));

            System.out.print("Product code: ");
            String code = input.nextLine();

            System.out.print("Product name: ");
            String name = input.nextLine();

            System.out.print("Unit price: ");
            double price = input.nextDouble();

            while (price < 0) {
                System.out.println("Price cannot be negative.");
                System.out.print("Unit price: ");
                price = input.nextDouble();
            }

            System.out.print("Opening stock: ");
            int stock = input.nextInt();

            while (stock < 0) {
                System.out.println("Stock cannot be negative.");
                System.out.print("Opening stock: ");
                stock = input.nextInt();
            }

            products[i] = new Product(code, name, price, stock);
            input.nextLine();
        }

        System.out.print("\nHow many transactions: ");
        int transactionNum = input.nextInt();
        input.nextLine();

        for (int i = 0; i < transactionNum; i++) {
            System.out.println("\nTransaction " + (i + 1));

            System.out.print("Product code: ");
            String code = input.nextLine();

            Product product = null;

            for (int j = 0; j < products.length; j++) {
                if (products[j].getCode().equalsIgnoreCase(code)) {
                    product = products[j];
                    break;
                }
            }

            if (product == null) {
                System.out.println("Product not found.");
            } else {
                System.out.print("R for restock or S for sell: ");
                String choice = input.nextLine();

                System.out.print("Quantity: ");
                int quantity = input.nextInt();
                input.nextLine();

                if (choice.equalsIgnoreCase("R")) {
                    if (product.restock(quantity)) {
                        System.out.println("Product restocked.");
                    } else {
                        System.out.println("Invalid quantity.");
                    }
                } else if (choice.equalsIgnoreCase("S")) {
                    if (product.sell(quantity)) {
                        System.out.println("Product sold.");
                    } else {
                        System.out.println("Not enough stock or invalid quantity.");
                    }
                } else {
                    System.out.println("Invalid transaction.");
                }
            }
        }

        double totalValue = 0;

        System.out.println("\n--- INVENTORY SUMMARY ---");

        for (int i = 0; i < products.length; i++) {
            System.out.println("\nProduct Code: " + products[i].getCode());
            System.out.println("Product Name: " + products[i].getName());
            System.out.println("Remaining Stock: " + products[i].getStock());
            System.out.println("Inventory Value: " + products[i].getInventoryValue());

            if (products[i].isLowStock()) {
                System.out.println("LOW STOCK");
            }

            totalValue = totalValue + products[i].getInventoryValue();
        }

        System.out.println("\nTotal Inventory Value: " + totalValue);
        System.out.println("Total Products Created: " + Product.getProductCount());

        input.close();
    }
}