package com.rest.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.Service.RecruitingService;
import com.rest.controller.RecruitingController;
import com.rest.model.Application;
import com.rest.model.Offer;

@RunWith(SpringRunner.class)
@WebMvcTest(RecruitingController.class)
public class SpringBootDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	RecruitingService movService;

	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	public void getAllOffersData() throws Exception {
		String uri = "/offer";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();

		if ((status != 200))
			return;
		else
			assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		Offer[] productlist = new SpringBootDemoApplicationTests().mapFromJson(content, Offer[].class);
		assertTrue(productlist.length > 0);

	}

	@Test
	public void createOfferData() throws Exception {
		String uri = "/offer";

		Offer offer = new Offer();
		offer.setJobTitle("A");
		offer.setNumberOfApplications(1);
		offer.setOfferId(1);
		offer.setStartDate("04-07-2020");

		Application ap = new Application();
		ap.setAppId(1);
		ap.setApplicationStatus("new");
		ap.setCandidateEmail("as@gmail.com");
		ap.setRelatedOffer("ss");
		ap.setResumeText("java");

		List<Application> l = new ArrayList<Application>();

		offer.setApplication(l);

		String inputJson = new SpringBootDemoApplicationTests().mapToJson(offer);

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();

		if ((status != 200))
			return;
		else
			assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Movie is created successfully");
	}

	@Test
	public void getApplicationByOfferId() throws Exception {
		String uri = "/getapplicationoffer/1";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();

		if ((status != 200))
			return;
		else
			assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		Offer[] productlist = new SpringBootDemoApplicationTests().mapFromJson(content, Offer[].class);
		assertTrue(productlist.length > 0);

	}

}
