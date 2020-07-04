package com.rest.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.Service.RecruitingService;
import com.rest.dao.ApplicationDAO;
import com.rest.dao.RecruitingDAO;
import com.rest.exception.ResourceNotFoundException;
import com.rest.model.Application;
import com.rest.model.Offer;

@Service
public class RecruiringServiceImpl implements RecruitingService {

	@Autowired
	RecruitingDAO recruitingDao;

	@Autowired
	ApplicationDAO applicationDAO;

	@Override
	public Offer getOfferById(Integer id) throws ResourceNotFoundException {

		Offer offer = recruitingDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Offer not found for this id :: " + id));
		return offer;
	}

	@Override
	public void createOffer(Offer offer, List<Application> application) {

		offer.setApplication(application);
		recruitingDao.save(offer);
		applicationDAO.saveAll(application);
	}

	@Override
	public List<Offer> getAllOfer() {
		List<Offer> offer = new ArrayList<Offer>();
		recruitingDao.findAll().forEach(offer::add);
		return offer;
	}

	@Override
	public Application getApplicationData(Integer id) throws ResourceNotFoundException {

		Application app = applicationDAO.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Application not found for this id :: " + id));
		return app;
	}

	@Override
	public void saveStatus(Offer offer, Application app) {
		List<Application> appList = new ArrayList<Application>();
		offer.setApplication(appList);
		recruitingDao.save(offer);
		applicationDAO.save(app);
	}

}
