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
    public boolean putOnParking(Car car) {
        boolean ret;
        int size = car.getCarSize();
        if (this.checkPlaces(size)) {
            this.truckLoad += 1;
            ret = true;
        } else {
            ret = false;
        }
        return ret;
    }

    @Override
    public Car getFromParking(Car car) {
        this.truckLoad -= 1;
        return car;
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
