package lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AbstractStore {

    public Warehouse(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Warehouse{" + "name='" + name + '\'' + '}';
    }
}
