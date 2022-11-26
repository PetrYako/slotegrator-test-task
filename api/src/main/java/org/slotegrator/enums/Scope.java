package org.slotegrator.enums;

public enum Scope {
    GUEST;

    public String getDefaultScope() {
        return String.format("%s:default", name().toLowerCase());
    }
}
