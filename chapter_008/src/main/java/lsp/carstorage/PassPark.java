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
    public void putOnParking(Car car) {
        this.passengerLoad += car.getCarSize();
    }

    @Override
    public void getFromParking(Car car) {
        this.passengerLoad -= car.getCarSize();
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



