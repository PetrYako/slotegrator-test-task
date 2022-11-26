package org.slotegrator.enums;

public enum GrantType {
    CLIENT_CREDENTIALS, PASSWORD;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
