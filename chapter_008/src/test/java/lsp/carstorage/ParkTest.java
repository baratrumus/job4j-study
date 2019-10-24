package lsp.carstorage;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ParkTest {
    LaunchParking park = new LaunchParking(2, 5);
    Car tr1 = new Truck(3);
    Car tr2 = new Truck(3);
    Car tr3 = new Truck(4);
    Car pass1 = new PassengerCar();
    Car pass2 = new PassengerCar();

    @Test
    public void testParking() {

        park.putCar(tr1);
        assertThat(park.truckPark.getLoad(), is(1));
        park.putCar(tr2);
        assertThat(park.truckPark.getLoad(), is(2));
        park.putCar(tr3);
        assertThat(park.passPark.getLoad(), is(4));
        park.putCar(pass1);
        assertThat(park.passPark.getLoad(), is(5));
        assertFalse(park.putCar(pass2));

    }

}
