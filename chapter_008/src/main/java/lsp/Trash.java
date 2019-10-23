package lsp;

public class Trash extends AbstractStore {

    public Trash(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Trash{" + "name='" + name + '\'' + '}';
    }


}
