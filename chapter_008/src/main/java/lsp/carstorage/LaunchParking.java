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

    public LaunchParking(int trSize, int passSize) {
        truckPark = new TruckPark(trSize);
        passPark = new PassPark(passSize);
    }

    public boolean putCar(Car car) {
        boolean ret = false;
        int carSize =  car.getCarSize();
        if (carSize == 1) {                     //легковая
            if (passPark.checkPlaces(carSize)) {
                passPark.putOnParking(car);
                ret = true;
            }
        } else {                                 //грузовая
            if (truckPark.checkPlaces(carSize)) {
                truckPark.putOnParking(car);
                ret = true;
            } else if (passPark.checkPlaces(carSize)) {
                passPark.putOnParking(car);
                ret = true;
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

