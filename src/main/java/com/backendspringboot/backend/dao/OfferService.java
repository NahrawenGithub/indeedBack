package com.backendspringboot.backend.dao;

import com.backendspringboot.backend.entity.Offer;
import com.backendspringboot.backend.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OfferService {

    @Autowired
    OfferRepository offerRepository;


    public List<Offer> getAllOffer() {
        return offerRepository.findAll();
    }


    public Offer insertOfferData(Offer offer) {
        return offerRepository.insert(offer);

    }

    public Optional<Offer> getOfferInformationById(String id) {
        return offerRepository.findById(id);

    }

    public Offer updateOffer(Offer o) {
        Optional<Offer> optionalOffer = Optional.ofNullable(o);
        if (optionalOffer.isPresent()) {
            return offerRepository.save(o);
        }
        return null;
    }
   public Offer createOffer(Offer o) {
            return offerRepository.save(o);
        }







    public ResponseEntity<HttpStatus> deleteOfferUsingId(String id) {
        try {
            offerRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Offer> findByDatePublished(Date date) {
        return offerRepository.findOffersByDatePosted(date);
    }
}
