public class Main {
    public static void main(String[] args) {
        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025",
                "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("iPhone 16 Pro", "15.09.2024",
                "Apple Inc.", "USA", 4999, false);
        productsArray[2] = new Product("Xiaomi 14", "20.01.2024",
                "Xiaomi", "China", 2999, true);
        productsArray[3] = new Product("Pixel 8 Pro", "10.10.2023",
                "Google", "USA", 3499, false);
        productsArray[4] = new Product("Galaxy Z Fold 6", "01.08.2024",
                "Samsung Corp.", "Korea", 4599, true);

        // Вывод информации о товарах
        for (Product product : productsArray) {
            product.printInfo();
        }
    }
}
