package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.client.GoogleCustomSearchClient;
import org.acme.client.GooglePlacesClient;
import org.acme.dto.PlacesInput;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigInteger;

@Path("/api/v1/prospect")
public class ProspectResource {


    @Inject
    @RestClient
    GoogleCustomSearchClient googleCustomSearchClient;

    @Inject
    @RestClient
    GooglePlacesClient googlePlacesClient;

    @ConfigProperty(name = "my.property.google.api.key")
    private String googleApiKey;

    @ConfigProperty(name = "my.property.google.search.engine.id")
    private String googleSearchEngine;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postSearch(@QueryParam("search") String search) {
        return googleCustomSearchClient.postSearch(googleApiKey, googleSearchEngine , search);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getUQs(@QueryParam("place") String place) {
        PlacesInput input = new PlacesInput();
        input.setTextQuery(place);
        input.setPageSize(BigInteger.valueOf(10));
        input.setPageToken("");
        return googlePlacesClient.getUQs(googleApiKey, "places.displayName,places.formattedAddress,places.websiteUri,nextPageToken",input );
    }
}
