package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigReader;
import config.ProjectConfiguration;
import config.WebConfig;
import helpers.Attach;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    private static final WebConfig webConfig = ConfigReader.Instance.read();

    @BeforeAll
    static void setUp() {
        ProjectConfiguration projectConfiguration = new ProjectConfiguration(webConfig);
        projectConfiguration.webConfig();

//        WebConfig webConfig = ConfigFactory.create(WebConfig.class);
//
//        Configuration.remote = webConfig.remoteUrl();
//        Configuration.browser = webConfig.browser().toString();
//        Configuration.browserVersion = webConfig.browserVersion();
//        Configuration.baseUrl = webConfig.baseUrl();
//        Configuration.browserSize = webConfig.browserSize();
//        Configuration.pageLoadStrategy = webConfig.pageLoadStrategy();

        //Configuration.remote = System.getProperty("remoteHost", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
        //Configuration.browser = System.getProperty("browser", "chrome");
        //Configuration.browserVersion = System.getProperty("browserVersion", "125.0.6422.142");
        //Configuration.baseUrl = System.getProperty("baseUrl", "https://dodopizza.ru/");
        //Configuration.browserSize = System.getProperty("browserSize", "1920x1080");

        //Configuration.timeout = 10000;

        //Configuration.browserSize = "1920x1080";
        //Configuration.pageLoadStrategy = "eager";
        WebDriverManager.chromedriver().setup();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @AfterEach
    void close() {
        closeWebDriver();
    }
}
