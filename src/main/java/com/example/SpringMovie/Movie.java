package com.example.SpringMovie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    String title;
    @JsonProperty
    String posterPath;
    String overview;
    double  popularity;
}
