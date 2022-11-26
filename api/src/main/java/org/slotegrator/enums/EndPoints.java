package org.slotegrator.enums;

public enum EndPoints {
    BASE_URI("http://test-api.d6.dev.devcaz.com/"),
    TOKEN("/v2/oauth2/token"),
    PLAYERS("/v2/players");

    private final String url;
    EndPoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
