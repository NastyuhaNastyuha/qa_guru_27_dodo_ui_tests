package config;

import com.codeborne.selenide.Configuration;

public class ProjectConfiguration {
    private final WebConfig webConfig;

    public ProjectConfiguration(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void webConfig() {
        Configuration.browser = webConfig.browser().toString();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.pageLoadStrategy = webConfig.pageLoadStrategy();
        Configuration.baseUrl = webConfig.baseUrl();
        if (webConfig.isRemote()) {
            Configuration.remote = webConfig.remoteUrl();
        }
    }
}