package com.arc.udemo.domain.events;

import com.arc.udemo.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "APICallEvent")
@Table(name = "api_calls")
public class APICallEvent extends BaseEntity implements Serializable {

    private String userEmail;
    private String ipAddress;
    private String domainName;
    private String location;
    private LocalDateTime requestTime;

    public APICallEvent(){}

    public APICallEvent(String ipAddress, String domainName, String location, LocalDateTime requestTime) {
        this.ipAddress = ipAddress;
        this.domainName = domainName;
        this.location = location;
        this.requestTime = requestTime;
    }

    public APICallEvent(String userEmail, String ipAddress, String domainName, String location, LocalDateTime requestTime) {
        this.userEmail = userEmail;
        this.ipAddress = ipAddress;
        this.domainName = domainName;
        this.location = location;
        this.requestTime = requestTime;
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

    @Override
    public String toString() {
        return "APICallEvent{" +
                "ipAddress='" + ipAddress + '\'' +
                ", domainName='" + domainName + '\'' +
                ", location='" + location + '\'' +
                ", requestTime=" + requestTime +
                '}';
    }
}
