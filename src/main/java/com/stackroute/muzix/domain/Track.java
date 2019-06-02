package com.stackroute.muzix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor                                                                     //allargument constructor annotation
@NoArgsConstructor                                                                      //noargument constructor annotation
public class Track {

    @Id
    private int trackId;                                                              //marking variable trackid using id annotation
    private String trackName;
    private String trackComments;

}
