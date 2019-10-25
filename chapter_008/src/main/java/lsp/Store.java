package lsp;

public interface Store { //стратегия
    public boolean accept(Food food);
    public void putToStore(Food food);
    public Food getFromStorage(Food food);
}
