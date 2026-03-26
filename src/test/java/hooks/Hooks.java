package hooks;

import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import utils.ConfigReader;
import utils.LoggerUtils;

public class Hooks {

    private static final Logger log = LoggerUtils.getLogger(Hooks.class);

    @Before
    public void setUp() {
        log.info("Launching browser");
        DriverFactory.initDriver(ConfigReader.getProperty("browser"));
        DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
    }

    @After
    public void tearDown() {
        log.info("Closing browser");
        DriverFactory.quitDriver();
    }
}
