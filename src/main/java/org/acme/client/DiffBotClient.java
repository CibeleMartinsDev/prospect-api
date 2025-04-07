package org.acme.client;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://api.diffbot.com/v3/article")
@ApplicationScoped
public interface DiffBotClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getContentNews(
            @QueryParam("token") String apiToken,
            @QueryParam("url") String url,
            @QueryParam("timeout") String timeout
    );
}
