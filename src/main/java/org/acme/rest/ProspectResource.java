package org.acme.rest;

import com.textrazor.annotations.AnalyzedText;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.client.GoogleCustomSearchClient;
import org.acme.client.GooglePlacesClient;
import org.acme.dto.PlacesInput;
import org.acme.services.ProspectService;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Path("/api/v1/prospect")
public class ProspectResource {


    @Inject
    @RestClient
    GoogleCustomSearchClient googleCustomSearchClient;

    @Inject
    @RestClient
    GooglePlacesClient googlePlacesClient;

    @Inject
    ProspectService prospectService;

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

    @GET
    @Path("/websitePages")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWebsitePages(@QueryParam("websiteUrl") String websiteUrl) throws Exception {
       Set<String> result =  prospectService.getPagesUQCustomerWebsite(websiteUrl);
      return Response.ok().entity(result).build() ;
    }

    @GET
    @Path("/webScraping")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWebScraping(@QueryParam("websiteUrl") String websiteUrl) throws Exception {
        List<String> list = new ArrayList<>();
        list.add(websiteUrl);
        String result =  prospectService.makeWebScraping(list);
        return Response.ok().entity(result).build() ;
    }

    @GET
    @Path("/contacts")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContacts(@QueryParam("websiteContent") String websiteContent) throws Exception {

        List<String> result =  prospectService.getContacts(websiteContent);
        return Response.ok().entity(result).build() ;
    }

}
