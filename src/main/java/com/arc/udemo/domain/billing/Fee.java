package com.arc.udemo.domain.billing;

import com.arc.udemo.domain.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Fees")
public class Fee extends NamedEntity {

    private int floor;
    private long ceiling;
    private double rate;
    private int unit;

    public Fee(){}

    public Fee(int floor, long ceiling, double rate, int unit) {
        this.floor = floor;
        this.ceiling = ceiling;
        this.rate = rate;
        this.unit = unit;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public long getCeiling() {
        return ceiling;
    }

    public void setCeiling(long ceiling) {
        this.ceiling = ceiling;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Fee{" +
                "floor=" + floor +
                ", ceiling=" + ceiling +
                ", rate=" + rate +
                ", unit=" + unit +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
