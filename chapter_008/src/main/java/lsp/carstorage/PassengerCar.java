package lsp.carstorage;

public class PassengerCar implements Car {
    int carSize;

    public PassengerCar() {
        this.carSize = 1;
    }

    @Override
    public int getCarSize() {
        return carSize;
    }
}
