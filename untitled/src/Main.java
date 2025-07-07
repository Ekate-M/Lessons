public static void main(String[] args) {
    // Пример работы с товарами
    Product[] productsArray = new Product[5];
    productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599, true);
    productsArray[1] = new Product("iPhone 16 Pro", "15.09.2024", "Apple Inc.", "USA", 6499, false);
    productsArray[2] = new Product("Xiaomi 14", "10.01.2024", "Xiaomi Inc.", "China", 3999, true);
    productsArray[3] = new Product("PlayStation 6", "20.11.2026", "Sony", "Japan", 7999, false);
    productsArray[4] = new Product("MacBook Pro M3", "05.03.2024", "Apple Inc.", "USA", 4599, true);

    System.out.println("=== Список товаров ===");
    for (Product product : productsArray) {
        product.printInfo();
    }

    // Пример работы с парком
    Park.Attraction[] attractions = {
            new Park.Attraction("Американские горки", "10:00-20:00", 450),
            new Park.Attraction("Колесо обозрения", "09:00-22:00", 250),
            new Park.Attraction("Ветерок", "11:00-19:00", 300)
    };

    Park myPark = new Park("Развлечений", attractions);
    System.out.println("\n=== Информация о парке ===");
    myPark.printParkInfo();
}
}