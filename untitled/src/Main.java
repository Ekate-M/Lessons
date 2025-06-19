import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Бобик");
        Dog dog2 = new Dog("Рекс");
        Cat cat1 = new Cat("Мурзик");
        Cat cat2 = new Cat("Барсик");
        Cat cat3 = new Cat("Васька");


        dog1.run(300);
        dog1.run(600);
        dog1.swim(5);
        dog1.swim(15);

        cat1.run(100);
        cat1.run(250);
        cat1.swim(10);


        Bowl bowl = new Bowl(25);

        ArrayList<Cat> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);


        for (Cat cat : cats) {
            cat.eat(bowl);
        }


        for (Cat cat : cats) {
            System.out.println(cat.getName() + " сытость: " + (cat.isFull() ? "сыт" : "голоден"));
        }


        bowl.addFood(15);
        cats.get(0).eat(bowl);
        cats.get(1).eat(bowl);


        System.out.println("\nСтатистика:");
        System.out.println("Всего животных: " + Animal.getTotalCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Котов: " + Cat.getCatCount());
        System.out.println(bowl);
    }
}
