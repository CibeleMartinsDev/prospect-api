package org.acme.dto;

import java.math.BigInteger;

public class PlacesInput {

    private String textQuery;

    private BigInteger pageSize;

    private String pageToken;

    public String getTextQuery() {
        return textQuery;
    }

    public void setTextQuery(String textQuery) {
        this.textQuery = textQuery;
    }

    public BigInteger getPageSize() {
        return pageSize;
    }

    public void setPageSize(BigInteger pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }
}
