package com.sprint.cbm.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity

public class Employees {
	@Id
	@Column(name = "employees_id")
	private int id;

	private String lastName;

	private String firstName;

	private String extension;

	private String email;

	private String jobTitle;

	@OneToMany(mappedBy = "employeeRep")
	@JsonManagedReference(value = "employees-customer")

	private List<Customer> customers;

	@OneToMany(mappedBy = "reportsTo", cascade = CascadeType.ALL)

	@JsonIgnore
	private List<Employees> reports;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reports_to_id")

	@JsonBackReference(value = "reports-reportsTo")
	private Employees reportsTo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "office_code")
	@JsonBackReference(value = "offices-employees")

	private Office offices;
	
	
	@JsonIgnore
	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private EmployeeAccount employeeAccount;

	public Employees() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Employees(int id, String lastName, String firstName, String extension, String email, String jobTitle,
			List<Customer> customers, List<Employees> reports, Employees reportsTo, Office offices,
			EmployeeAccount employeeAccount) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.extension = extension;
		this.email = email;
		this.jobTitle = jobTitle;
		this.customers = customers;
		this.reports = reports;
		this.reportsTo = reportsTo;
		this.offices = offices;
		this.employeeAccount = employeeAccount;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Employees> getReports() {
		return reports;
	}

	public void setReports(List<Employees> reports) {
		this.reports = reports;
	}

	public Employees getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(Employees reportsTo) {
		this.reportsTo = reportsTo;
	}

	public Office getOffices() {
		return offices;
	}

	public void setOffices(Office offices) {
		this.offices = offices;
	}

	public EmployeeAccount getEmployeeAccount() {
		return employeeAccount;
	}

	public void setEmployeeAccount(EmployeeAccount employeeAccount) {
		this.employeeAccount = employeeAccount;
	}

	@Override
	public String toString() {
		return "Employees [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", extension="
				+ extension + ", email=" + email + ", jobTitle=" + jobTitle + ", customers=" + customers + ", reports="
				+ reports + ", reportsTo=" + reportsTo + ", offices=" + offices + ", employeeAccount=" + employeeAccount
				+ "]";
	}
	

}