package lsp.carstorage;

public class TruckPark implements Parking {
    //private final int[] parkingTruck;
    private final int truckPlaces;
    private int truckLoad;

    public TruckPark(int size) {
        //this.parkingTruck = new int[size];
        this.truckPlaces = size;
        this.truckLoad = 0;
    }

    @Override
    public void putOnParking(Car car) {
        this.truckLoad += 1;
    }

    @Override
    public void getFromParking(Car car) {
        this.truckLoad -= 1;
    }

    @Override
    public boolean checkPlaces(int size) {
        return (this.truckLoad < this.truckPlaces);
    }

    @Override
    public int getLoad() {
        return this.truckLoad;
    }

}
