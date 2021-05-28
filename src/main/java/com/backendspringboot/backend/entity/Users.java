package com.backendspringboot.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.geometry.Pos;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "Users")
public class Users {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private String role;
    private Number telephone;
    private String addresse;
    private String fullName;
    @JsonIgnore
    @OneToMany(mappedBy =  "user",cascade = CascadeType.ALL)
    private Set<Offer> offers;

    @JsonIgnore
    @OneToMany(mappedBy =  "user",cascade = CascadeType.ALL)
    private Set<PostutedOffer> postutedOffers;


}