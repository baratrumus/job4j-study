package lsp;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 Создать класс Food сполями. Name, expaireDate, createDate, price, disscount. На основе класса Food создать другие продукты.
 Создать классы хранилище продуктов Warehouse, Shop, Trash.
 Создать класс обработчик перераспределения продуктов в место использования. ControllQuality. Класс должен перераспределять еду по хранилищам в зависимости от условиый.
 3.1. Если срок годности израсходован меньше чем на 25% направить в Warehouse.
 3.2 Если срок годности от 25% до 75% направить в Shop
 3.3. Если срок годности больше 75% то выставить скидку на продукт и отправить в Shop
 3.4. Если срок годности вышел. Отправить продукт в мусорку.
 В данной задаче надо использовать шаблон стратегия
 */
public class Food {
    private String name;
    private LocalDate expireDate;

    private LocalDate createDate;
    private int price;
    private int discount;
    private AbstractStore whereIsProduct;

    public Food(String name, LocalDate expireDate, LocalDate createDate, int price, int discount) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{" + "name='" + name + '\'' + ", whereIsProduct=" + whereIsProduct + '}';
    }

    public AbstractStore getWhereIsProduct() {
        return this.whereIsProduct;
    }

    public void setWhereIsProduct(AbstractStore store) {
        this.whereIsProduct = store;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
