class Bowl {
    private int foodAmount;

    public Bowl(int initialFood) {
        this.foodAmount = Math.max(initialFood, 0);
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void decreaseFood(int amount) {
        if (amount > 0) {
            foodAmount = Math.max(foodAmount - amount, 0);
        }
    }

    public void addFood(int amount) {
        if (amount > 0) {
            foodAmount += amount;
            System.out.println("В миску добавили " + amount + " еды. Теперь в миске " + foodAmount + " еды.");
        } else {
            System.out.println("Нельзя добавить отрицательное количество еды");
        }
    }

    @Override
    public String toString() {
        return "В миске осталось " + foodAmount + " еды";
    }
}
