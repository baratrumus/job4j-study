package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ivannikov Ilya (voldores@mail.ru)
 * @version $id
 * @since 0.1
 */

public class Seat {

    @JsonProperty("row")
    private String row;

    @JsonProperty("column")
    private String column;

    public Seat(String row, String column) {
        this.row = row;
        this.column = column;
    }

    public Seat() {
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

    public void setColumn(String blockcolumn) {
        this.column = blockcolumn;
    }
}
