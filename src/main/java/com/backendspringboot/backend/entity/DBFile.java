package com.backendspringboot.backend.entity;


import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "DBFile")
public class DBFile {
    @Id
    private String id;
    private String fileName;
    private String fileType;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] data;

    @JsonIgnore
    @OneToOne
    private PostutedOffer postedofferCv;

    public DBFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;

    }
}