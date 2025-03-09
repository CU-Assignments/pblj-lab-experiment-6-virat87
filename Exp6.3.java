import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Optional;

class Product {
    String name;
    String category;
    double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public void display() {
        System.out.println(name + " (" + category + ") - Price: $" + price);
    }
}

public class ProductProcessor {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200),
            new Product("Phone", "Electronics", 800),
            new Product("TV", "Electronics", 1500),
            new Product("T-Shirt", "Clothing", 40),
            new Product("Jeans", "Clothing", 60),
            new Product("Sneakers", "Footwear", 120),
            new Product("Boots", "Footwear", 120)
        );

        processProducts(products);

        runTestCases();
    }

    public static void processProducts(List<Product> products) {
       
        Map<String, List<Product>> groupedByCategory = products.stream()
                .collect(Collectors.groupingBy(p -> p.category));

        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
                .collect(Collectors.groupingBy(p -> p.category,
                        Collectors.maxBy(Comparator.comparingDouble(p -> p.price))));

    
        double averagePrice = products.stream()
                .collect(Collectors.averagingDouble(p -> p.price));

      
        System.out.println("=== Grouped Products by Category ===");
        groupedByCategory.forEach((category, productList) -> {
            System.out.println(category + ": " + productList.stream()
                    .map(p -> p.name)
                    .collect(Collectors.joining(", ")));
        });

        System.out.println("\n=== Most Expensive Product in Each Category ===");
        mostExpensiveByCategory.forEach((category, product) -> 
            System.out.println(category + ": " + (product.isPresent() ? product.get().name + " - $" + product.get().price : "No products")));

        System.out.println("\n=== Average Price of All Products ===");
        System.out.printf("Average Price: $%.2f%n", averagePrice);
    }

    public static void runTestCases() {
        System.out.println("\n===== Running Test Cases =====");

        // Test Case 1: Normal Case
        System.out.println("\nTest Case 1: Normal Case");
        processProducts(Arrays.asList(
                new Product("Laptop", "Electronics", 1200),
                new Product("Phone", "Electronics", 800),
                new Product("TV", "Electronics", 1500),
                new Product("T-Shirt", "Clothing", 40),
                new Product("Jeans", "Clothing", 60),
                new Product("Sneakers", "Footwear", 120),
                new Product("Boots", "Footwear", 120)
        ));

        // Test Case 2: Single Category Only
        System.out.println("\nTest Case 2: Single Category Only");
        processProducts(Arrays.asList(
                new Product("Laptop", "Electronics", 1200),
                new Product("Phone", "Electronics", 800),
                new Product("TV", "Electronics", 1500)
        ));

        // Test Case 3: Same Price in a Category
        System.out.println("\nTest Case 3: Same Price in a Category");
        processProducts(Arrays.asList(
                new Product("Sneakers", "Footwear", 120),
                new Product("Boots", "Footwear", 120)
        ));

        // Test Case 4: Only One Product
        System.out.println("\nTest Case 4: Only One Product");
        processProducts(Arrays.asList(
                new Product("Laptop", "Electronics", 1200)
        ));

        // Test Case 5: Empty List
        System.out.println("\nTest Case 5: Empty List");
        processProducts(Collections.emptyList());
    }
}
