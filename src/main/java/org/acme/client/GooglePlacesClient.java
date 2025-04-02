package org.acme.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.dto.PlacesInput;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient
@ApplicationScoped
public interface GooglePlacesClient {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    String getUQs(@HeaderParam("X-Goog-Api-Key") String apiKey,
                    @HeaderParam("X-Goog-FieldMask") String fieldMask,
                    PlacesInput body);
}
