package org.slotegrator.config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/test/resources/user.properties"})
public interface Properties extends org.aeonbits.owner.Config {

    @org.aeonbits.owner.Config.Key("guest.username")
    String guestUsername();
}
