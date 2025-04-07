package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.EmailExtractor;
import org.acme.LinkExtractor;
import org.acme.client.DiffBotClient;
import org.acme.dto.UQCustomerDTO;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ProspectService {

    @Inject
    LinkExtractor linkExtractor;

    @Inject
    EmailExtractor emailExtractor;

    @ConfigProperty(name = "my.property.diffbot.api.key")
    String apiDiffbotToken;

    @ConfigProperty(name = "my.property.text-razor.api.key")
    String textRazorToken;

    @Inject
    @RestClient
    DiffBotClient diffBotClient;

    public Set<String> getPagesUQCustomerWebsite(String websiteUrl) throws Exception {
//        ProspectCrawler prospectCrawler = new ProspectCrawler();
        UQCustomerDTO uqCustomerDTO = new UQCustomerDTO();
        uqCustomerDTO.setWebsiteUrl(websiteUrl);
        return linkExtractor.extractLinks(uqCustomerDTO.getWebsiteUrl());
    }

    public String makeWebScraping(List<String> websitePages){
        String resultWebScraping = "";
        for(String page : websitePages){
            resultWebScraping = diffBotClient.getContentNews(apiDiffbotToken, page, "5000");
        }
        return resultWebScraping;
    }



    public List<String> getContacts(String url){
        return emailExtractor.extractEmails(url);
    }
}
