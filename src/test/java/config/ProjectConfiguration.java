package config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class ProjectConfiguration {
    private final WebConfig webConfig;

    public ProjectConfiguration(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void webConfig() {
//        ChromeOptions chromeOptions = new ChromeOptions();
//
//        chromeOptions.addArguments("--disable-popup-blocking");
        //WebDriver driver = new ChromeDriver(chromeOptions);

        Configuration.browser = webConfig.browser().toString();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.pageLoadStrategy = webConfig.pageLoadStrategy();
        Configuration.baseUrl = webConfig.baseUrl();
        if(webConfig.isRemote()) {
            Configuration.remote = webConfig.remoteUrl();
        }
    }

}
