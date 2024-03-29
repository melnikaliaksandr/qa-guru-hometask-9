package config;

import org.aeonbits.owner.Config;

@Config.Sources({ "classpath:config/driver.properties" })
public interface DriverConfig extends Config {

    @Key("url")
    String getUrl();

    @Key("remote.web.user")
    String getRemoteWebUser();

    @Key("remote.web.password")
    String getRemoteWebPassword();

}
