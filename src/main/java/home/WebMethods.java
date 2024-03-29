package home;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.devtools.v120.network.Network;
import org.openqa.selenium.devtools.v120.network.model.ConnectionType;
import java.util.Optional;

import java.time.Duration;


public class WebMethods {
    /**
     * used for default duration of waiting before finding element
     */
    final int defaultDuration = 10;

    /**
     * Perform action to click button or clickable element,
     * will wait until the element is enabled.
     * @param id locator of element
     * @param driver driver used on the method
     */
    public void clickOn(By id, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(defaultDuration));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(id));
        button.click();
    }

    /**
     * Perform type action of input field elements,
     * will wait until element is displayed.
     * @param id locator of element
     * @param driver driver used on the method
     * @param text user input text
     */
    public void typeON(By id, String text, WebDriver driver) {
        waitUntilDisplayed(id, driver, defaultDuration);
        WebElement field = driver.findElement(id);
        field.clear();
        field.sendKeys(text);
    }

    /**
     * Perform checking if element status is displayed and returning true condition
     * @param id locator of element
     * @param driver driver used on the method
     * @param duration used on the method as how long driver should wait
     * @return return condition
     */
    public boolean waitUntilDisplayed(By id, WebDriver driver, int duration) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(id));
        return element.isDisplayed();
    }

    /**
     * Perform checking if element status is enabled and returning true condition
     * @param id locator of element
     * @param driver driver used on the method
     * @return return condition
     */
    public boolean waitUntilEnabled(By id, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(defaultDuration));
        wait.pollingEvery(Duration.ofMillis(defaultDuration));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(id));
        return element.isEnabled();
    }

    /**
     * Perform waiting condition until the element is gone/invisible.
     * @param driver driver used on the method
     * @param id locator of element
     */
    public boolean waitUntilInvisible(By id, WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(defaultDuration));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(id));
        return true;
    }

    /**
     * Perform checking if toast is appeared and returning true condition
     * @param id locator of element
     * @param driver driver used on the method
     * @return return condition
     */
    public String checkToast(By id, WebDriver driver) {
        WebElement toast = driver.findElement(id);
        return toast.getAttribute("name");
    }

    /**
     * Perform Pressing Enter Keyboard on Element
     * @param driver driver used on the method
     * @param id locator of element
     */
    public void pressEnter(By id, WebDriver driver){
        waitUntilDisplayed(id, driver, defaultDuration);
        WebElement element = driver.findElement(id);
        element.sendKeys(Keys.ENTER);
    }

    /**
     * Perform get text of an element and return string
     * @param id locator of element
     * @param driver driver used on the method
     * @return text of the element in string
     */
    public String getText(By id, WebDriver driver){
        waitUntilDisplayed(id, driver, defaultDuration);
        return driver.findElement(id).getText();
    }

    /**
     * Perform iframe switch for selenium
     * @param id locator of the element
     * @param driver driver used on the method
     */
    public void switchIframe(By id, WebDriver driver) {
        waitUntilEnabled(id, driver);
        WebElement iframe = driver.findElement(id);
        driver.switchTo().frame(iframe);
    }

    /**
     * Perform checking whether an element is selected or not
     * @param id locator of the element
     * @param driver driver used on the method
     * @return true if the element is selected
     */
    public boolean selectedByUser(By id, WebDriver driver){
        WebElement isSelected = driver.findElement(id);
        return isSelected.isSelected();
    }

    /**
     * Perform scroll to element
     * @param id locator of the element
     * @param driver driver used on the method
     */
    public void scrollToView(By id, WebDriver driver){
        WebElement element = driver.findElement(id);
        Actions actions = new Actions(driver);
        actions.scrollToElement(element);
        actions.perform();
    }

    /**
     * Perform scroll using js script
     * @param id locator of the element
     * @param driver driver used on the method
     */
    public void jsScrollToView (By id, WebDriver driver){
        WebElement element = driver.findElement(id);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     *   Simulate 2g network on driver
     *   @param driver driver used on the method
     */
    public void slowTestToCheckAvailablelity (WebDriver driver){
        DevTools devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.of(1000000), Optional.empty(),

                Optional.empty()));

        devTools.send(
                Network.emulateNetworkConditions(false, 100, 10000, 10000,
                        Optional.of(ConnectionType.CELLULAR2G)));
           }

    /**
     * Perform reset on driver network speed
     * @param driver driver used on the method
     */
    public void resetDriverTestNetwork(WebDriver driver){
        DevTools devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.clearAcceptedEncodingsOverride());
    }

}
