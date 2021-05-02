package com.arc.udemo.rest.dto;

import java.util.Set;

public class UsageSubscriptionRequest {

    private String title;
    private String email;
    private String firstName;
    private String lastName;
    private Set<String> roles;
    private String plan;

    public UsageSubscriptionRequest(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPlan() {
        return plan;
    }

    public void sePlan(String plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "UsageSubscriptionRequestDTO{" +
                "email='" + email + '\'' +
                ", plan='" + plan + '\'' +
                '}';
    }
}
