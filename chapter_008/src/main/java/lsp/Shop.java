package lsp;

public class Shop extends AbstractStore {
    public Shop(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Shop{" + "name='" + name + '\'' + '}';
    }
}
