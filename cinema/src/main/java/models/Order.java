package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class Order {

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("row")
    private String row;

    @JsonProperty("column")
    private String column;

    public Order(String phone, String fullName, String row, String column) {
        this.phone = phone;
        this.fullName = fullName;
        this.row = row;
        this.column = column;
    }

    public Order() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}