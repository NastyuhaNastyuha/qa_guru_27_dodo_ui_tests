//package config;
//
//import com.codeborne.selenide.Configuration;
//
//public class ProjectConfiguration {
//    private final WebConfig webConfig;
//    private final AuthConfig authConfig;
//
//    public ProjectConfiguration(WebConfig webConfig, AuthConfig authConfig) {
//        this.webConfig = webConfig;
//        this.authConfig = authConfig;
//    }
//
//
//
//    public void webConfig() {
//        Configuration.browser = webConfig.browser().toString();
//        Configuration.browserVersion = webConfig.browserVersion();
//        Configuration.browserSize = webConfig.browserSize();
//        Configuration.pageLoadStrategy = webConfig.pageLoadStrategy();
//        Configuration.baseUrl = webConfig.baseUrl();
//        if (webConfig.isRemote()) {
//            Configuration.remote = webConfig.remoteUrl();
//        }
//    }
//}