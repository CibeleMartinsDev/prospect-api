package org.acme.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.PlacesInput;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@ApplicationScoped
public interface RDStationClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
String getOrganizations(@QueryParam("token") String token);
}
