package pages;

import consts.Consts;

import java.io.IOException;

public class HomePage extends BasePage {
    public static final String LOGO = "//img[@alt='coming soon']";
    public static final String ABOUT_US = "//*[contains(text(),'About Us')]";
    public static final String LOG_IN = "//*[text()='Log in/Register']";
    public static final String FIND_JOB = "//*[@name='Find Job']";

    public void navigateToHomePage() {
        webDriver.get(Consts.MAIN_URL);
    }

    public boolean isLogoLoaded() {
        boolean isLogoVisible = isElementExist(LOGO);
        return isLogoVisible;
    }

    public void captureLogo() throws IOException {
        captureElement(LOGO, "Logo.png");
    }
    public AboutUsPage clickAboutUsPage(){
        clickElement(ABOUT_US);
        return new AboutUsPage();
    }
    public LoginPage clickLoginPage(){
        clickElement(LOG_IN);
        return new LoginPage();
    }
    public FindJobPage clickFindJobPage(){
        clickElement(FIND_JOB);
        return new FindJobPage();
    }
}
