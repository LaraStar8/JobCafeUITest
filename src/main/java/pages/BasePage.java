package pages;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BasePage {

    protected static WebDriver webDriver;
    protected static WebDriverWait wait;

    private static final Logger logger = LogManager.getLogger(BasePage.class);

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }

    protected WebElement findElementByXpath(String xpath) {
        logger.info("Finding element " + xpath);
        WebElement element = webDriver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return element;
    }

    protected void clickElement(String xpath) {
        logger.info("Clicking element: " + xpath);
        findElementByXpath(xpath).click();
    }

    protected void sendKeyToElement(String xpath, String text) {
        findElementByXpath(xpath).sendKeys(text);
    }

    protected void captureElement(String xpath, String fileName) throws IOException {
        WebElement logo = findElementByXpath(xpath);
        File file = logo.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(fileName));
    }

    protected boolean isElementExist(String xpath) {
        try {
            logger.info("Check element with xpath exists: " + xpath);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            return true;
        } catch (Exception Error) {
            return false;
        }
    }

    protected String getCurrentURL() {
        return webDriver.getCurrentUrl();
    }

    protected boolean checkTheList(String xpath, String parameter) throws InterruptedException {
        boolean correct = false;
        Thread.sleep(1000);
        List<WebElement> list = webDriver.findElements(By.xpath(xpath));

        for (WebElement locationList : list) {
            String text = locationList.getText();
            if (text.contains(parameter)) {
                correct = true;
            }
        }
        return correct;
    }
}

