package com.arc.udemo.domain.billing;

import com.arc.udemo.domain.NamedEntity;
import com.arc.udemo.domain.users.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bills")
public class Bill extends NamedEntity {

    @OneToOne
    private User user;
    @JsonIgnore
    @OneToOne
    private Band band;
    private double subTotal;
    private double tax;
    private double total;
    @Column(name = "bill_status")
    @Enumerated(EnumType.STRING)
    private BillStatus billStatus;
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date date_created;
    @Column(name = "date_dispatched")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date dispatched;

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

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public Date getDispatched() {
        return dispatched;
    }

    public void setDispatched(Date dispatched) {
        this.dispatched = dispatched;
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
