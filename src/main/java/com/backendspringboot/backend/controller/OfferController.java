package com.backendspringboot.backend.controller;

import com.backendspringboot.backend.dao.OfferService;
import com.backendspringboot.backend.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/offers/")
public class OfferController {
        @Autowired
        private OfferService offerService ;

        @GetMapping("getAllOffers")
        private Object getAllOffers()
        {
            return ResponseEntity.status(HttpStatus.OK).body(offerService.getAllOffer());
        }
        @PostMapping("CreateOffer")
        public Object createOffer(@RequestBody Offer o)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(offerService.createOffer(o));
        }

        @PutMapping("UpdateOffers")
        public Object updateOffer(@RequestBody Offer o)
        {
            return ResponseEntity.status(HttpStatus.OK).body(offerService.updateOffer(o));
        }

        @DeleteMapping("DeleteOffer/{id}")
        public ResponseEntity<HttpStatus> deleteOffer(@PathVariable("id") String id)
        {
            return offerService.deleteOfferUsingId(id);
        }
    }

