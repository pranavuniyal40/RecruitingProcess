package com.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.Service.RecruitingService;
import com.rest.exception.ResourceNotFoundException;
import com.rest.model.Application;
import com.rest.model.Offer;
import com.rest.model.Recruiting;

@RestController
public class RecruitingController {

	@Autowired
	private RecruitingService recruitingService;

	@GetMapping(value = "/offer")
	public ResponseEntity<List<Offer>> getAllOffers() throws ResourceNotFoundException {
		try {
			List<Offer> offer = recruitingService.getAllOfer();

			if (offer.isEmpty()) {
				return new ResponseEntity<List<Offer>>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<List<Offer>>(offer, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/offer/{id}")
	public ResponseEntity<Offer> getOfferById(@PathVariable("id") Integer id) throws ResourceNotFoundException {

		Offer offerData = recruitingService.getOfferById(id);
		return ResponseEntity.ok().body(offerData);
	}

	@PostMapping(value = "/offer")
	public ResponseEntity<Offer> createOffer(@Valid @RequestBody Recruiting req) {
		try {

			Offer offer = req.getOffer();
			List<Application> appList = req.getApp();

			recruitingService.createOffer(offer, appList);

			return new ResponseEntity<Offer>(HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping(value = "/getapplicationoffer/{id}")
	public ResponseEntity<List<Application>> getApplicationByOfferId(@PathVariable("id") Integer id)
			throws ResourceNotFoundException {

		Offer offerData = recruitingService.getOfferById(id);

		List<Application> appList = new ArrayList<Application>();
		if (offerData.getOfferId() != null) {
			appList = offerData.getApplication();

		}

		return new ResponseEntity<List<Application>>(appList, HttpStatus.OK);
	}

	@PostMapping(value = "/applyForOffer/{id}")
	public ResponseEntity<String> applyForOffer(@PathVariable("id") Integer id, @RequestParam("status") String status)
			throws ResourceNotFoundException {

		Offer offerData = recruitingService.getOfferById(id);
		String message = "";
		List<Integer> appId = new ArrayList<Integer>();
		if (offerData.getOfferId() != null) {

			List<Integer> idList = offerData.getApplication().stream().map(Application::getAppId)
					.collect(Collectors.toList());

			for (Integer i : idList) {
				Application app = recruitingService.getApplicationData(i);

				if (app.getApplicationStatus().equals("new")) {

					app.setApplicationStatus(status);
					recruitingService.saveStatus(offerData, app);
					message = "Applied Successfully";
					return new ResponseEntity<String>(message, HttpStatus.OK);

				} else {
					message = "Application already Applied by someone else";
				}
			}

		}

		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
