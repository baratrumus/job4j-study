package lsp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public abstract class AbstractStore implements Store {


    private final List<Food> foods;
    public final String name;
    private final LocalDate actualDate;

    public AbstractStore(String name) {
        this.name = name;
        this.foods = new ArrayList<>();
        actualDate = LocalDate.now();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Store{" + "name='").append(name).append('\'').append("} :");
        for (Food food : foods) {
            sb.append(food + ", ");
        }
        return sb.toString();
    }

    @Override
    public void putToStore(Food food) {
        foods.add(food);
    }

    @Override
    public Food getFromStore(Food food) {
        foods.remove(food);
        return food;
    }

    @Override
    public List<Food> showFood() {
        return foods;
    }

    @Override
    public void emptyStore() {
        foods.clear();
    }

    /**
     * Проверяет подходит ли продукт в конкретное хранилище
     * @param food
     * @return
     */
    @Override
    public boolean accept(Food food) {
     return true;
    }

    public Integer getActualityPercent(Food food) {
        Long lengthOfLife = DAYS.between(food.getCreateDate(), food.getExpireDate());
        Long daysElapsed = DAYS.between(food.getCreateDate(), actualDate);

        Double actualityPercent =  daysElapsed.doubleValue() / lengthOfLife.doubleValue() * 100;
        return actualityPercent.intValue();
    }

}

