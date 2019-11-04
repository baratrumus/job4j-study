package di;


import lsp.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ResortTest {
    Food banana = new Food("Banana", LocalDate.of(2019, 11, 05), LocalDate.of(2019, 05, 01), 20, 2);
    Food bread = new Food("Bread", LocalDate.of(2019, 5, 25), LocalDate.of(2018, 7, 01), 20, 2);
    Food milk = new Food("Milk", LocalDate.of(2020, 05, 10), LocalDate.of(2019, 10, 01), 20, 2);

    Store warehouse = new Warehouse("warehouse");
    Store shop = new Shop("shop");
    Store trash = new Trash("trash");
    ControllQuality cq = new ControllQuality(Arrays.asList(warehouse, shop, trash));
    FoodResort resort = new FoodResort(Arrays.asList(warehouse, shop, trash));


    @Test
    public void whenResortFoodItsResorted() {
        cq.control(banana);
        cq.control(bread);
        cq.control(milk);

        assertThat(warehouse.showFood().get(0), is(milk));
        assertThat(trash.showFood().get(0), is(banana));
        assertThat(trash.showFood().get(1), is(bread));

        resort.resort();

        assertThat(warehouse.showFood().get(0), is(milk));
        assertThat(trash.showFood().get(0), is(banana));
        assertThat(trash.showFood().get(1), is(bread));
    }
}

