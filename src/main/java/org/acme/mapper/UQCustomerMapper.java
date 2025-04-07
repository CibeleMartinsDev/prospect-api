package org.acme.mapper;

import org.acme.dto.Place;
import org.acme.dto.PlacesOutput;
import org.acme.dto.UQCustomerDTO;

import java.util.ArrayList;
import java.util.List;

public class UQCustomerMapper {

    public static List<UQCustomerDTO> from (PlacesOutput places){
        List<UQCustomerDTO> uqs = new ArrayList<>();
        for(Place place : places.getPlaces()){
            UQCustomerDTO uq = from(place);
            uqs.add(uq);
        }
        return uqs;
    }

    public static UQCustomerDTO from (Place place){
        UQCustomerDTO uq = new UQCustomerDTO();
        uq.setName(place.getDisplayName().getText());
        uq.setWebsiteUrl(place.getWebsiteUri());
        return uq;
    }
}
