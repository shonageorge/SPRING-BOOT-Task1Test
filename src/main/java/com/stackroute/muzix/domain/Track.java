package com.stackroute.muzix.domain;

//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

//import javax.persistence.Entity;
//import javax.persistence.Id;

@Document(collection="Track")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Track {

    @Id
    private int trackId;
    private String trackName;
    private String trackComments;

}
