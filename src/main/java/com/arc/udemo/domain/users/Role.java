package com.arc.udemo.domain.users;

import com.arc.udemo.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users = new HashSet<>();

    @Column( name = "role")
    private String name;

    public Role(){}

    public Role(String role){
        name = role;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "users=" + users +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
