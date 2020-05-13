package lsp;

import java.time.LocalDate;
import java.util.Arrays;

/*
Существует парковка для грузовых и легковых машин. Количество парковочных мест для каждого типа машин задается на этапе создания парковки.
Легковая машина может занять только место, предназначенное для легковой машины. Грузовая машина может разместиться на месте, предназначенном для грузовых машин, либо на N парковочных мест для легковых машин, стоящих рядом.
Необходимо разработать сервис для учета парковки машин.
Разбейте реализацию этой задачи на 3 этапа.
1. Создание интерфейсов и схемы взаимодействия этих интерфейсов.
2. Реализация тестов.
3. Реализация кода.
 */

public class Run {

    public static void main(String[] args) {

        Food banana = new Food("Banana", LocalDate.of(2019, 11, 05), LocalDate.of(2019, 05, 01), 20, 2);
        Food bread = new Food("Bread", LocalDate.of(2019, 5, 25), LocalDate.of(2018, 7, 01), 20, 2);
        Food milk = new Food("Milk", LocalDate.of(2020, 05, 10), LocalDate.of(2019, 10, 01), 20, 2);

       Store warehouse = new Warehouse("warehouse");
       Store shop = new Shop("shop");
       Store trash = new Trash("trash");

        ControllQuality cq = new ControllQuality(Arrays.asList(warehouse, shop, trash));
        cq.control(banana);
        cq.control(bread);
        cq.control(milk);
        System.out.println(warehouse);
        System.out.println(shop);
        System.out.println(trash);
    }
}
