package com.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "application")
public class Application {

	@Id
	@Column(name = "appId")
	@NotNull
	private Integer appId;

	private String relatedOffer;

	@Email
	@NotBlank
	private String candidateEmail;

	private String resumeText;

	private String applicationStatus;

	public Application() {

	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getRelatedOffer() {
		return relatedOffer;
	}

	public void setRelatedOffer(String relatedOffer) {
		this.relatedOffer = relatedOffer;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getResumeText() {
		return resumeText;
	}

	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

}
