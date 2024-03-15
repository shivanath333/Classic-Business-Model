package com.sprint.cbm.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_account")
public class EmployeeAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employees_id", referencedColumnName = "employees_id")
    private Employees employee;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String role;
    
    private boolean isActive;

	public EmployeeAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeAccount(Long id, String email, Employees employee, String password, String role, boolean isActive) {
		super();
		this.id = id;
		this.email = email;
		this.employee = employee;
		this.password = password;
		this.role = role;
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Employees getEmployee() {
		return employee;
	}

	public void setEmployee(Employees employee) {
		this.employee = employee;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole() {
		this.role = "ADMIN";
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive() {
		this.isActive = true;
	}

	@Override
	public String toString() {
		return "EmployeeAccount [id=" + id + ", email=" + email + ", employee=" + employee + ", password=" + password
				+ ", role=" + role + ", isActive=" + isActive + "]";
	}
	
    

	
	
	
	
	
}
