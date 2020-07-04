package com.rest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rest.model.Offer;

@Repository
public interface  RecruitingDAO extends CrudRepository<Offer, Integer>
{

}
