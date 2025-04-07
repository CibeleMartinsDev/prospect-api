package org.acme.dto;

import java.util.List;

public class UQCustomerDTO {

    private String name;

    private String websiteUrl;

    private List<UQWebSitePageDTO> websitePages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public List<UQWebSitePageDTO> getWebsitePages() {
        return websitePages;
    }

    public void setWebsitePages(List<UQWebSitePageDTO> websitePages) {
        this.websitePages = websitePages;
    }
}
