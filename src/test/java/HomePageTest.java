import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.AboutUsPage;
import pages.FindJobPage;
import pages.HomePage;
import pages.LoginPage;
import utils.UseCaseBase;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class HomePageTest extends UseCaseBase {
    private static HomePage homePage;
    public static final Logger logger = LogManager.getLogger(HomePageTest.class);


    @BeforeAll
    public static void classSetup() {
        homePage = new HomePage();
    }

    @BeforeEach
    public void beforeTest() {
        homePage.navigateToHomePage();
    }

    @Test
    public void homePageLoadingTest() throws IOException {
        logger.info("Home Page Load Test");
        boolean isLogoVisible = homePage.isLogoLoaded();
        homePage.captureLogo();
        assertTrue(isLogoVisible);
    }
    @Test
    public void openAboutUsTest(){
        AboutUsPage aboutUsPage = homePage.clickAboutUsPage();
        String expected = "http://167.99.178.249:3000/about";
        String actual = aboutUsPage.getAboutUsURL();
        assertEquals(expected, actual);
    }
    @Test
    public void openLoginTest(){
        LoginPage loginPage = homePage.clickLoginPage();
        String expected = "http://167.99.178.249:3000/login";
        String actual = loginPage.getLoginURL();
        assertEquals(expected, actual);
    }
    @Test
    public void openFindJobTest(){
        FindJobPage findJobPage = homePage.clickFindJobPage();
        String expected = "http://167.99.178.249:3000/job-page";
        String actual = findJobPage.getFindJobURL();
        assertEquals(expected, actual);
    }
}
