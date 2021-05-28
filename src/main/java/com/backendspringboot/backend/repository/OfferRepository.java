package com.backendspringboot.backend.repository;

import com.backendspringboot.backend.entity.Help.JobTitle;
import com.backendspringboot.backend.entity.Offer;
import com.backendspringboot.backend.entity.Help.SeniorityLevel;
import com.backendspringboot.backend.entity.Help.Skills;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface OfferRepository extends MongoRepository<Offer,String> {
    Optional<Offer> findById(String id);
    void deleteById(String id);
    List<Offer> findOffersByDatePosted(Date date);
}
