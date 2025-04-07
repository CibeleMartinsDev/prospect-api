package org.acme.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.client.GoogleCustomSearchClient;
import org.acme.client.GooglePlacesClient;
import org.acme.client.RDStationClient;
import org.acme.dto.PlacesInput;
import org.acme.dto.PlacesOutput;
import org.acme.dto.RDStationOutput;
import org.acme.dto.UQCustomerDTO;
import org.acme.mapper.UQCustomerMapper;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProspectService {

    private final Integer MAX_UQS = 40;

    private String nextPagePlaces = new String();

    @Inject
    @RestClient
    GooglePlacesClient googlePlacesClient;

    @Inject
    @RestClient
    RDStationClient rdStationClient;

    @ConfigProperty(name = "my.property.google.api.key")
    private String googleApiKey;

    @ConfigProperty(name = "my.property.rd-station-api-key")
    private String crmApiKey;


    public List<UQCustomerDTO> getUQs(String searchValue) throws JsonProcessingException {

        List<UQCustomerDTO> uqs = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        String uqsInCrm = rdStationClient.getOrganizations("Bearer " + crmApiKey, "application/json");
        RDStationOutput uqsInCrmMapped = objectMapper.readValue(uqsInCrm, RDStationOutput.class);
        System.out.println("Cliente/Empresas do CRM: " + uqsInCrmMapped);

        while (uqs.size() < 40) {
            PlacesInput input = new PlacesInput();
            input.setTextQuery(searchValue);
            input.setPageSize(BigInteger.valueOf(20));
            input.setPageToken(nextPagePlaces);

            String places = googlePlacesClient.getUQs(googleApiKey, "places.displayName,places.formattedAddress,places.websiteUri,nextPageToken",input );

            PlacesOutput placesMapped = objectMapper.readValue(places,  PlacesOutput.class);
            List<UQCustomerDTO> placesConvertedUqs = UQCustomerMapper.from(placesMapped);
            uqs.addAll(placesConvertedUqs);
            nextPagePlaces = placesMapped.getNextPageToken();
        }
        System.out.println("Tamanho lista de UQs: " + uqs.size());
        return uqs;
    }


}
