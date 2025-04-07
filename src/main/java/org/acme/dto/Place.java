package org.acme.dto;

public class Place {

    private String formattedAddress;
    private String websiteUri;
    private DisplayName displayName;

    // Construtor
    public Place() {

    }

    // Getters e Setters
    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getWebsiteUri() {
        return websiteUri;
    }

    public void setWebsiteUri(String websiteUri) {
        this.websiteUri = websiteUri;
    }

    public DisplayName getDisplayName() {
        return displayName;
    }

    public void setDisplayName(DisplayName displayName) {
        this.displayName = displayName;
    }

    // Classe interna para representar o DisplayName
    public static class DisplayName {
        private String text;
        private String languageCode;

        // Construtor
        public DisplayName() {
        }

        // Getters e Setters
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getLanguageCode() {
            return languageCode;
        }

        public void setLanguageCode(String languageCode) {
            this.languageCode = languageCode;
        }
    }
}
