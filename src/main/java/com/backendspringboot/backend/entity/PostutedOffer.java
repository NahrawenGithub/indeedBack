package com.backendspringboot.backend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collation = "PostuledOffer")
public class PostutedOffer {

    @Id
    private String id ;

    @OneToOne(mappedBy = "postedofferCv",cascade = CascadeType.ALL)
    private DBFile cv ;

    @ManyToOne
    private Users users ;

    @ManyToOne
    private Offer offer;
}
