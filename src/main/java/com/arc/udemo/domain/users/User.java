package com.arc.udemo.domain.users;

import com.arc.udemo.domain.BaseEntity;
import com.arc.udemo.domain.billing.Band;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author bolajisalau
 * User is a simple pojo that describes the security identity  and personal information about an individual
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="users")
public class User extends BaseEntity implements Serializable {

	@Column(name = "title")
	@Enumerated(EnumType.STRING)
	private Title title;

	@Column(name = "email")
    @NotEmpty(message = "email must not be empty")
	private String email;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private Boolean enabled = false;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name = "mobile_phone")
	@Digits(fraction = 0, integer = 11)
	private String mobilePhone;

	@Column(name = "date_registered")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date dateRegistered = new Date();

	@Column(name = "date_verified")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date dateVerified;

	@Column(name = "date_deactivated")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date dateDeactivated;

	@JsonIgnore
	@Column(name="verified")
	private boolean verified = false;

	@JsonIgnore
	@Column(name="admin")
	private boolean admin = false;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private UserStatus status = UserStatus.REGISTERED;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Role.class)
	@JoinTable(name="user_role",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"))
	protected Collection<Role> roles = new HashSet<>();

	@JsonIgnore
	@OneToOne
	private Band band;

	public User() {}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
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

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Date getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(Date dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public Date getDateVerified() {
		return dateVerified;
	}

	public void setDateVerified(Date dateVerified) {
		this.dateVerified = dateVerified;
	}

	public Date getDateDeactivated() {
		return dateDeactivated;
	}

	public void setDateDeactivated(Date dateDeactivated) {
		this.dateDeactivated = dateDeactivated;
	}

	public boolean isVerified() {
		return verified;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
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

	@Override
	public String toString() {
		return getId() + ", " + getEmail() + ", " + getFirstName() + ", " + getLastName();
	}

}
