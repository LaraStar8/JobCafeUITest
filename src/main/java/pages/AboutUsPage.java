package pages;

import consts.Consts;

public class AboutUsPage extends BasePage{
    private static final String LOGO = "//h2[text()=' CAFE']";

    public String getAboutUsURL(){
        return getCurrentURL();
    }
    public boolean isLogoVisible(){
        return isElementExist(LOGO);
    }
    public void navigateToAboutUsPage(){
        webDriver.get(Consts.MAIN_URL);
        clickElement("//*[@name='About Us']");
    }
}
