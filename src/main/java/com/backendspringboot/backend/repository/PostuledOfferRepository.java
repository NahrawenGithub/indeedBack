package com.backendspringboot.backend.repository;

import com.backendspringboot.backend.entity.PostutedOffer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostuledOfferRepository extends MongoRepository<PostutedOffer , String> {
}
