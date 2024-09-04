package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:auth.properties"
})
public interface AuthConfig extends Config {
    @Key("remoteUrl")
    String remoteUrl();
}
