package org.slotegrator.core.props;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/main/resources/admin.properties"})
public interface Properties extends Config {

    @Key("baseURL")
    String getUrl();

    @Key("admin.password")
    String adminPass();
}
