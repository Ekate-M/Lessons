import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Создаем животных
        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Рекс");
        Cat cat1 = new Cat("Мурзик");
        Cat cat2 = new Cat("Барсик");
        Cat cat3 = new Cat("Васька");

        // Тестируем бег и плавание
        dog1.run(300);
        dog1.run(600);
        dog1.swim(5);
        dog1.swim(15);

        cat1.run(100);
        cat1.run(250);
        cat1.swim(10);

        // Работа с миской и котами
        Bowl bowl = new Bowl(25); // Создаем миску с 25 единицами еды

        ArrayList<Cat> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);

        // Кормим всех котов
        for (Cat cat : cats) {
            cat.eat(bowl);
        }

        // Проверяем сытость
        for (Cat cat : cats) {
            System.out.println(cat.getName() + " сытость: " + (cat.isFull() ? "сыт" : "голоден"));
        }

        // Добавляем еды и пробуем покормить снова
        bowl.addFood(15);
        cats.get(0).eat(bowl); // Попробуем покормить уже сытого кота
        cats.get(1).eat(bowl); // А этот был голоден

        // Выводим статистику
        System.out.println("\nСтатистика:");
        System.out.println("Всего животных: " + Animal.getTotalCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Котов: " + Cat.getCatCount());
        System.out.println(bowl);
    }
}
