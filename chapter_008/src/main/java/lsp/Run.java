package lsp;

import java.time.LocalDate;

public class Run {

    public static void main(String[] args) {

        Food banana = new Food("Banana", LocalDate.of(2019, 11, 05), LocalDate.of(2019, 05, 01), 20, 2);
        Food bread = new Food("Bread", LocalDate.of(2019, 5, 25), LocalDate.of(2018, 7, 01), 20, 2);
        Food milk = new Food("Milk", LocalDate.of(2020, 05, 10), LocalDate.of(2019, 10, 01), 20, 2);

      //  "Bread in %s, Banana in %s, Milk in %s"
        ControllQuality cq = new ControllQuality();
        cq.checkQuality(banana);
        cq.checkQuality(bread);
        cq.checkQuality(milk);
        System.out.println(banana);
        System.out.println(bread);
        System.out.println(milk);
    }
}
