package ru.job4j.bank;

import java.util.Objects;

public class Account {
    private double values;
    private String requisites;
    /**
     *  @param values кол-во денег
     * @param requisites реквизиты счёта
     */
    public Account(double values, String requisites) {
        this.values = values;
        this.requisites = requisites;
    }
    public double getValues() {
        return this.values;
    }
    public String getReqs() {
        return this.requisites;
    }

    public void setValues(double values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return getValues() == account.getValues()
                && Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValues(), requisites);
    }
}
