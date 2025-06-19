class Cat extends Animal {
    private static int catCount = 0;
    private final int maxRunDistance = 200;
    private boolean fullness = false;

    public Cat(String name) {
        super(name);
        catCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(getName() + " пробежал " + distance + " м.");
        } else {
            System.out.println(getName() + " не может пробежать " + distance + " м. (максимум " + maxRunDistance + " м.)");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(getName() + " не умеет плавать");
    }

    public void eat(Bowl bowl) {
        if (bowl.getFoodAmount() >= 10 && !fullness) {
            bowl.decreaseFood(10);
            fullness = true;
            System.out.println(getName() + " поел из миски");
        } else {
            System.out.println(getName() + " не стал есть (мало еды или уже сыт)");
        }
    }

    public boolean isFull() {
        return fullness;
    }

    public static int getCatCount() {
        return catCount;
    }
}
