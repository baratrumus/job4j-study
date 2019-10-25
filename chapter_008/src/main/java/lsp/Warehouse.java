package lsp;


public class Warehouse extends AbstractStore {
    public Warehouse(String name) {
        super(name);
    }

    @Override
    public boolean accept(Food food) {
        Integer percent = super.getActualityPercent(food);
        boolean ret = false;
        if (percent < 25) {
            ret = true;
        }
        return ret;
    }

}
