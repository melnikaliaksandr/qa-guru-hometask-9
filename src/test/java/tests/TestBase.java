package tests;

import com.codeborne.selenide.Configuration;
import config.DriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentHelper.*;

public class TestBase {

    static DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.browser = System.getProperty("web.browser", "chrome");

        String remoteWebDriver = System.getProperty("remote.web.driver");
        if (remoteWebDriver != null) {
            String user = driverConfig.getRemoteWebUser();
            String password = driverConfig.getRemoteWebPassword();
            Configuration.remote = String.format(remoteWebDriver, user, password);
        }
    }

    @AfterEach
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        if (System.getProperty("video.storage") != null)
            attachVideo();
        closeWebDriver();
    }
}
