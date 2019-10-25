package lsp;

public class Trash extends AbstractStore {

    public Trash(String name) {
        super(name);
    }

    @Override
    public boolean accept(Food food) {
        Integer percent = super.getActualityPercent(food);
        boolean ret = false;
        if (percent >= 100) {
            ret = true;
        }
        return ret;
    }

 }
