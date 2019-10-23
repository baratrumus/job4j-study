package lsp;

public interface Store {
    public void putToStore(Food food);
    public Food getFromStorage(Food food);
}
