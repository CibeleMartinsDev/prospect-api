package org.acme.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.services.ProspectService;


@Path("/api/v1/prospect")
public class ProspectResource {

    @Inject
    ProspectService prospectService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUQs(@QueryParam("searchValue") String searchValue) throws Exception {
        return Response.ok(prospectService.getUQs(searchValue)).build();
    }

}
