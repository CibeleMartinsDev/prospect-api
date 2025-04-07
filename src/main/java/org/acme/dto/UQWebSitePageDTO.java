package org.acme.dto;

public class UQWebSitePageDTO {

    private String url;

    private String content;

    private String contactsExtractedsContent;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContactsExtractedsContent() {
        return contactsExtractedsContent;
    }

    public void setContactsExtractedsContent(String contactsExtractedsContent) {
        this.contactsExtractedsContent = contactsExtractedsContent;
    }
}
