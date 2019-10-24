package lsp;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private List<Food> foods;
    public String name;

    public AbstractStore(String name) {
        this.name = name;
        this.foods = new ArrayList<>();
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void putToStore(Food food) {
        foods.add(food);
    }

    @Override
    public Food getFromStorage(Food food) {
        foods.remove(food);
        return food;
    }

}

