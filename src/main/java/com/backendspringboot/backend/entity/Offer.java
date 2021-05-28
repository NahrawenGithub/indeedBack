package com.backendspringboot.backend.entity;

import com.backendspringboot.backend.entity.Help.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Offer")
public class Offer {
    @Id
    private String id;
    private JobTitle title ;
    private String description ;
    private JobType type ;
    private Date DatePosted ;
    private Skills competence;
    private SeniorityLevel niveau ;
    private Location location;

    
    @ManyToOne
    private Users users ;

    @JsonIgnore
    @OneToMany(mappedBy =  "offer",cascade = CascadeType.ALL)
    private Set<PostutedOffer> postutedOffers;
}