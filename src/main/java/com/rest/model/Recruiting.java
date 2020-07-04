package com.rest.model;

import java.util.List;

public class Recruiting {

	private Offer offer;
	private List<Application> app;

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public List<Application> getApp() {
		return app;
	}

	public void setApp(List<Application> app) {
		this.app = app;
	}

}
