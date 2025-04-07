package org.acme.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.PlacesInput;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@ApplicationScoped
@Path("/organizations")
public interface RDStationClient {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    String getOrganizations(@HeaderParam("Authorization") String apiKey,
                  @HeaderParam("Accept") String accept);
}
