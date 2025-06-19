class Animal {
    private String name;
    private static int totalCount = 0;

    public Animal(String name) {
        this.name = name;
        totalCount++;
    }

    public void run(int distance) {}
    public void swim(int distance) {}

    public String getName() {
        return name;
    }

    public static int getTotalCount() {
        return totalCount;
    }
}
