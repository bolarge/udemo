package com.arc.udemo.domain.billing;

import com.arc.udemo.domain.NamedEntity;
import com.arc.udemo.domain.users.User;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bills")
public class Bill extends NamedEntity {

    @OneToOne
    private User user;

    @OneToOne
    private Band band;
    private double subTotal;
    private double tax;
    private double total;

    public Bill(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "user=" + user +
                ", usagePlan=" + band +
                ", subTotal=" + subTotal +
                ", tax=" + tax +
                ", total=" + total +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
