package com.Battleship.Game.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import com.Battleship.Game.models.CoordinateStatus;

@Embeddable
public class Coordinate {

    //Attributes
    @Column(name = "coordinate_row")
    private int row;
    @Column(name = "coordinate_column")
    private int column;
    @Column
    private CoordinateStatus status;

    //Constructors
    public Coordinate() {
    }

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
        this.status = CoordinateStatus.UNTOUCHED;
    }

    public Coordinate(int row, int column, CoordinateStatus status) {
        this.row = row;
        this.column = column;
        this.status = status;
    }

    //Getters and setters
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public CoordinateStatus getStatus() {
        return status;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setStatus(CoordinateStatus status) {
        this.status = status;
    }
}
