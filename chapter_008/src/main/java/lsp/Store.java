package lsp;

import java.util.List;

public interface Store { //стратегия
    public boolean accept(Food food);
    public void putToStore(Food food);
    public Food getFromStore(Food food);
    public List<Food> showFood();
    public void emptyStore();
}
