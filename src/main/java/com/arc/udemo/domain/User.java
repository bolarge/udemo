package com.arc.udemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * @author bolajisalau
 * User is a simple pojo that describes the security identity of a Person
 */

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name = "username")
    @NotEmpty //(message = "Username must not be empty")
    //@Size(min = 6, max = 16)
	private String username;

	@Column(name = "password")
    @NotEmpty
	private String password;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Role> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	@JsonIgnore
	public void addRole(String roleName) {
		if(this.roles == null) {
			this.roles = new HashSet<>();
		}
		Role role = new Role();
		role.setName(roleName);
		this.roles.add(role);
	}
}
