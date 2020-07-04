package com.rest.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "offer")
public class Offer {

	@Id
	@Column(name = "offerId")
	@NotNull
	private Integer offerId;

	@Column(unique = true)
	private String jobTitle;

	private String startDate;

	private int numberOfApplications;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "offer_application", joinColumns = { @JoinColumn(name = "offerId") }, inverseJoinColumns = {
			@JoinColumn(name = "appId") })
	private List<Application> application;

	public Offer() {

	}

	public Offer(@UniqueElements String jobTitle, String startDate, int numberOfApplications) {
		super();
		this.jobTitle = jobTitle;
		this.startDate = startDate;
		this.numberOfApplications = numberOfApplications;
	}

	public Integer getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getNumberOfApplications() {
		return numberOfApplications;
	}

	public void setNumberOfApplications(int numberOfApplications) {
		this.numberOfApplications = numberOfApplications;
	}

	public List<Application> getApplication() {
		return application;
	}

	public void setApplication(List<Application> application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return "Offer [offerId=" + offerId + ", jobTitle=" + jobTitle + ", startDate=" + startDate
				+ ", numberOfApplications=" + numberOfApplications + "]";
	}

}
