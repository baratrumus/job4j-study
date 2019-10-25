package lsp;

public class Shop extends AbstractStore {
    public Shop(String name) {
        super(name);
    }

    @Override
    public boolean accept(Food food) {
        Integer percent = super.getActualityPercent(food);
        boolean ret = false;
        if ((percent >= 25) && (percent <= 75)) {
            ret = true;
        } else if ((percent > 75) && (percent < 100)) {
            food.setDiscount(5);
            ret = true;
        }
        return ret;
    }

}
