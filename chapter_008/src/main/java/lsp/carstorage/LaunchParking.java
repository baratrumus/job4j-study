package lsp.carstorage;

/**
 * Существует парковка для грузовых и легковых машин. Количество парковочных мест для каждого типа машин
 * задается на этапе создания парковки. Легковая машина может занять только место, предназначенное для легковой машины.
 * Грузовая машина может разместиться на месте, предназначенном для грузовых машин, либо на N парковочных мест
 * для легковых машин, стоящих рядом. Необходимо разработать сервис для учета парковки машин.
 */
public class LaunchParking {
    Parking truckPark;
    Parking passPark;

    public LaunchParking(int truckSize, int passSize) {
        truckPark = new TruckPark(truckSize);
        passPark = new PassPark(passSize);
    }

    public boolean putCar(Car car) {
        boolean ret = false;
        int carSize =  car.getCarSize();
        if (carSize == 1) {                     //легковая
            ret = passPark.putOnParking(car);
        } else {                                 //грузовая
            if (truckPark.checkPlaces(carSize)) {
                ret = truckPark.putOnParking(car);
            } else if (passPark.checkPlaces(carSize)) {
                ret = passPark.putOnParking(car);
            }
        }
        if (ret) {
            System.out.println("Car parked");
        } else {
            System.out.println("No places");
        }
        return ret;
    }

    public static void main(String[] args) {
        LaunchParking park = new LaunchParking(2, 5);
        Car tr1 = new Truck(3);
        Car tr2 = new Truck(3);
        Car tr3 = new Truck(4);
        Car pass1 = new PassengerCar();
        Car pass2 = new PassengerCar();

        park.putCar(tr1);
        park.putCar(tr2);
        park.putCar(tr3);
        park.putCar(pass1);
        park.putCar(pass2);
    }
}

