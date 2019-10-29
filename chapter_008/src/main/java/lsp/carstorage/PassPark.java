package lsp.carstorage;

public class PassPark implements Parking {
    //public final int[] parkingPassenger;
    private final int passengerCarPlaces;
    private int passengerLoad;

    public PassPark(int size) {
        //this.parkingPassenger = new int[size];
        this.passengerCarPlaces = size;
        this.passengerLoad = 0;
    }

    @Override
    public boolean putOnParking(Car car) {
        boolean ret;
        int size = car.getCarSize();
        if (this.checkPlaces(size)) {
            this.passengerLoad += size;
            ret = true;
        } else {
            ret = false;
        }
        return ret;
    }

    @Override
    public Car getFromParking(Car car) {
        this.passengerLoad -= car.getCarSize();
        return car;
    }

    @Override
    public boolean checkPlaces(int size) {
        return ((this.passengerLoad + size) <= this.passengerCarPlaces);
    }

    @Override
    public int getLoad() {
        return this.passengerLoad;
    }


}



