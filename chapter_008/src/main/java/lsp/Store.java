package lsp;

public interface Store { //стратегия
    public void putToStore(Food food);
    public Food getFromStorage(Food food);
}
