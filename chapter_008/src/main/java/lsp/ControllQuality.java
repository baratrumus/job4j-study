package lsp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;

import static java.time.temporal.ChronoUnit.DAYS;

public class ControllQuality {

    private LocalDate actualDate = LocalDate.now();

    public void checkQuality(Food food) {
        Integer percent = getActualityPercent(food);
        if (percent < 25) {
            food.setWhereIsProduct(new Warehouse("warehouse"));
        } else if ((percent >= 25) && (percent <= 75)) {
            food.setWhereIsProduct(new Shop("shop"));
        } else if ((percent > 75) && (percent < 100)) {
            food.setDiscount(5);
            food.setWhereIsProduct(new Shop("shop"));
        } else if (percent >= 100) {
            food.setWhereIsProduct(new Trash("trash"));
        }
    }

    private Integer getActualityPercent(Food food) {
        Long lengthOfLife = DAYS.between(food.getCreateDate(), food.getExpireDate());
        Long daysElapsed = DAYS.between(food.getCreateDate(), actualDate);

        Double actualityPercent =  daysElapsed.doubleValue() / lengthOfLife.doubleValue() * 100;
        return actualityPercent.intValue();
    }


}
