package di;

import lsp.ControllQuality;
import lsp.Food;
import lsp.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Взять код из проекта Хранилище продуктов.
 * 1. Необходимо добавить динамическое распределение продуктов.
 * 2. В классе ControllQuality добавить метод resort();
 * он должен извлекать все продукты и перераспределять их заново.
 */
public class FoodResort extends ControllQuality {

    private List<Store> stores;

    public FoodResort(List<Store> stores) {
        super(stores);
        this.stores = stores;
    }

    public void resort() {
        for (Food food : getFoodFromStores()) {
            control(food);
        }
    }

    private List<Food> getFoodFromStores() {
        List<Food> ret = new ArrayList<>();
        for (Store str : stores) {
            for (Food food : str.showFood()) {
                ret.add(food);
            }
            str.emptyStore();
        }
        return ret;
    }

}
