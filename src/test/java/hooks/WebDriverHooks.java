package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class WebDriverHooks {
    @Before
    public void initializeWebDriver(){
        home.webdriver.WebDriverInstance.initialize();
    }


    @After
    public void quitWebDriver(){
        home.webdriver.WebDriverInstance.quit();
    }
}
