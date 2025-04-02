package org.acme.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient
@ApplicationScoped
public interface GoogleCustomSearchClient {
    @GET
    String postSearch( @QueryParam("key") String key, @QueryParam("cx") String cx, @QueryParam("q") String q );
}
