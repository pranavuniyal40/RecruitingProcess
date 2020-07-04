package com.rest.Service;

import java.util.List;

import javax.validation.Valid;

import com.rest.exception.ResourceNotFoundException;
import com.rest.model.Application;
import com.rest.model.Offer;

public interface RecruitingService {


	Offer getOfferById(Integer id) throws ResourceNotFoundException;

	void createOffer(Offer offer,  @Valid List<Application> appList);

	List<Offer> getAllOfer();

	Application getApplicationData(Integer i) throws ResourceNotFoundException;

	void saveStatus(Offer offer,Application app);

}
