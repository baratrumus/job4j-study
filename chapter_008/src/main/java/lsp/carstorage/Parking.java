package lsp.carstorage;

public interface Parking {
    public void putOnParking(Car car);
    public void getFromParking(Car car);
    public int getLoad();
    public boolean checkPlaces(int size);
}
