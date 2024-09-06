package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.AuthConfig;
import config.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setUp() {

        AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());
        WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());
        Configuration.browser = webConfig.browser();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.pageLoadStrategy = webConfig.pageLoadStrategy();
        Configuration.baseUrl = webConfig.baseUrl();

        if (webConfig.isRemote()) {
            Configuration.remote = authConfig.remoteUrl();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options",
                    Map.<String, Object>of("enableVNC", true, "enableVideo", true));
            Configuration.browserCapabilities = capabilities;
        }

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