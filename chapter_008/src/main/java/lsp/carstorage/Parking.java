package lsp.carstorage;

public interface Parking {
    public boolean putOnParking(Car car);
    public Car getFromParking(Car car);
    public int getLoad();
    public boolean checkPlaces(int size);
}
