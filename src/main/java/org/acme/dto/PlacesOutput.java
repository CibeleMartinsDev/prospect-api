package org.acme.dto;

import java.util.List;

public class PlacesOutput {

    private List<Place> places;
    private String nextPageToken;

    public PlacesOutput(){

    }

    // Getters e Setters
    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }
}
