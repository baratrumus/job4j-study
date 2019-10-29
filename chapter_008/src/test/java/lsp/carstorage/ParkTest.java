package lsp.carstorage;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ParkTest {
    LaunchParking park = new LaunchParking(2, 5);

    @Test
    public void whenGetTruckCarSizeItsGot() {
        Car tr1 = new Truck(3);
        assertThat(tr1.getCarSize(), is(3));
    }

    @Test
    public void whenTruckOnParkingParkLoadIncreased() {
        Car tr1 = new Truck(3);
        park.putCar(tr1);
        assertThat(park.truckPark.getLoad(), is(1));
        assertThat(park.passPark.getLoad(), is(3));
    }

    @Test
    public void whenTruckOnParkingAndNoPlacesItPlacedToPassPark() {
        Car tr1 = new Truck(3);
        Car tr2 = new Truck(3);
        Car tr3 = new Truck(4);
        park.putCar(tr1);
        park.putCar(tr3);
        park.putCar(tr3);
        assertThat(park.truckPark.getLoad(), is(2));
        assertThat(park.passPark.getLoad(), is(4));
    }

    @Test
    public void whenGetFromParkingLoadDecreased() {
        Car pass1 = new PassengerCar();
        Car pass2 = new PassengerCar();
        park.putCar(pass1);
        park.putCar(pass2);
        assertThat(park.passPark.getLoad(), is(2));
        park.passPark.getFromParking(pass1);
        assertThat(park.passPark.getLoad(), is(1));
    }
}
