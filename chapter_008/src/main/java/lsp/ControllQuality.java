package lsp;


import java.util.ArrayList;
import java.util.List;


public class ControllQuality {

    List<Store> stores;

    public ControllQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void control(Food food) {
        for (Store str : stores) {
            if (str.accept(food)) {
                str.putToStore(food);
            }
        }
    }



}
