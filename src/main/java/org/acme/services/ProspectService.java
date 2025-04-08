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
import org.jboss.logging.Logger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProspectService {

    private final Integer MAX_UQS = 40;

    private String nextPagePlaces = "";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = Logger.getLogger(ProspectService.class);

    @ConfigProperty(name = "my.property.google.api.key")
    private String googleApiKey;

    @ConfigProperty(name = "my.property.rd-station-api-key")
    private String crmApiKey;

    @Inject
    @RestClient
    GooglePlacesClient googlePlacesClient;

    @Inject
    @RestClient
    RDStationClient rdStationClient;

    @Inject
    ExcelService excelService;


    public String getUQs(String searchValue) throws Exception {

        List<UQCustomerDTO> uqs = new ArrayList<>();
        RDStationOutput uqsInCrmMapped = getUQsInCrm();

        while (uqs.size() < 40) {
            //Pega os lugares/empresas
            PlacesOutput placesMapped = getPlaces(searchValue);
            //Realiza o mapper desses lugares/empresas para um objeto Java
            List<UQCustomerDTO> placesConvertedUqs = UQCustomerMapper.from(placesMapped);
            //Adiciona na lista de UQs, somente empresas que ainda não existem na base do CRM
            for(UQCustomerDTO uq : placesConvertedUqs) {
                if(!uq.getWebsiteUrl().isEmpty()){
                    addUqsNotExistsCrm(uqs,uq, uqsInCrmMapped);
                }
            }
            nextPagePlaces = placesMapped.getNextPageToken();
        }
        System.out.println("Tamanho lista de UQs: " + uqs.size());
        //Cria a planilha
        return excelService.generateExcel(uqs, "UQs - Região");
    }

    public RDStationOutput getUQsInCrm() throws Exception {
        try{
            String uqsInCrm = rdStationClient.getOrganizations(crmApiKey);
            return objectMapper.readValue(uqsInCrm, RDStationOutput.class);
        }catch (Exception e) {
            throw new Exception("Houve um problema ao acessar os UQs do CRM.");
        }
    }

    public PlacesOutput getPlaces(String place) throws Exception {
        try{
            PlacesInput input = new PlacesInput();
            input.setTextQuery(place);
            input.setPageSize(BigInteger.valueOf(20));
            input.setPageToken(nextPagePlaces);

            String places = googlePlacesClient.getUQs(googleApiKey, "places.displayName,places.formattedAddress,places.websiteUri,nextPageToken",input );
            return objectMapper.readValue(places,  PlacesOutput.class);
        } catch (Exception e) {
            throw new Exception("Houve um problema ao acessar os lugares." + e.getMessage());
        }
    }

    public void addUqsNotExistsCrm(List<UQCustomerDTO> uqs, UQCustomerDTO uq, RDStationOutput uqsInCrmMapped){

            if(!uqsInCrmMapped.getOrganizations().isEmpty()){
                for(RDStationOutput.Organization organization : uqsInCrmMapped.getOrganizations()) {
                    if (uq.getName().equalsIgnoreCase(organization.getName())) {
                        log.info("Essa empresa/UQ já existe no CRM.");
                    } else {
                        uqs.add(uq);
                    }
                }
            }else {
                uqs.add(uq);
            }

    }
}
