package lsp.carstorage;

public class Truck implements Car {
    int carSize;

    public Truck(int size) {
        this.carSize = size;
    }

    @Override
    public int getCarSize() {
        return carSize;
    }
}
