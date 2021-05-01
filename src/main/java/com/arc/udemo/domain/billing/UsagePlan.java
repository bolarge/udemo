package com.arc.udemo.domain.billing;

import com.arc.udemo.domain.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usage_plans")
public class UsagePlan extends NamedEntity {

    @OneToOne
    private Fee fee;
    private double price;

    public UsagePlan() {
    }

    public UsagePlan(String name, Fee fee) {
        super(name);
        this.fee = fee;
    }

    public UsagePlan(String name, String description, double price, Fee fee) {
        super(name, description);
        this.price = price;
        this.fee = fee;
    }

    public Fee getFee() {
        return fee;
    }

    public void setFee(Fee fee) {
        this.fee = fee;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "fee=" + fee +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
