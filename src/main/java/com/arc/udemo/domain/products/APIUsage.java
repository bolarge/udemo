package com.arc.udemo.domain.products;

import com.arc.udemo.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "APIEvent")
@Table(name = "usage")
public class APIUsage extends BaseEntity  {

    private String userEmail;
    private String ipAddress;
    private String domainName;
    private String location;
    private LocalDateTime requestTime;
    private LocalDate requestDate;

    public APIUsage(){}

    public APIUsage(String userEmail, String ipAddress, String domainName, String location, LocalDate requestDate) {
        this.userEmail = userEmail;
        this.ipAddress = ipAddress;
        this.domainName = domainName;
        this.location = location;
        this.requestDate = requestDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return "APICallEvent{" +
                "ipAddress='" + ipAddress + '\'' +
                ", domainName='" + domainName + '\'' +
                ", location='" + location + '\'' +
                ", requestDate=" + requestDate +
                '}';
    }
}
