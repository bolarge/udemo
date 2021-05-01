package com.arc.udemo.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class NamedEntity extends BaseEntity {

    @Column(name = "name")
    protected String name;

    @Column(name = "description")
    protected String description;

    @Column(name="status")
    protected boolean status = false;

    public NamedEntity(){}

    public NamedEntity(String name){
        this.name = name;
    }

    public NamedEntity(String name, String description) {
        this(name);
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NamedEntity{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
