package com.app.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.Enum.MaintenanceStatus;

@Entity
@Table(name = "maintenance_requests")
public class MaintenanceRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String issueDescription;

	@Enumerated(EnumType.STRING)
	private MaintenanceStatus status;

	@ManyToOne
	@JoinColumn(name = "tenant_id", nullable = false)
	private User tenant;

	@ManyToOne
	@JoinColumn(name = "property_id", nullable = false)
	private Properties property;

	@Column(nullable = false)
	private LocalDate reportedDate;

	@Column
	private LocalDate resolvedDate;

	public MaintenanceRequest() {
		super();
	}

	public MaintenanceRequest(Long id, String issueDescription, MaintenanceStatus status, User tenant,
			Properties property, LocalDate reportedDate, LocalDate resolvedDate) {
		super();
		this.id = id;
		this.issueDescription = issueDescription;
		this.status = status;
		this.tenant = tenant;
		this.property = property;
		this.reportedDate = reportedDate;
		this.resolvedDate = resolvedDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public MaintenanceStatus getStatus() {
		return status;
	}

	public void setStatus(MaintenanceStatus status) {
		this.status = status;
	}

	public User getTenant() {
		return tenant;
	}

	public void setTenant(User tenant) {
		this.tenant = tenant;
	}

	public Properties getProperty() {
		return property;
	}

	public void setProperty(Properties property) {
		this.property = property;
	}

	public LocalDate getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(LocalDate reportedDate) {
		this.reportedDate = reportedDate;
	}

	public LocalDate getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(LocalDate resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

}
