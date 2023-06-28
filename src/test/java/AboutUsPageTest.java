import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.AboutUsPage;
import pages.HomePage;
import utils.UseCaseBase;

import static org.junit.jupiter.api.Assertions.*;

public class AboutUsPageTest extends UseCaseBase {
    private static AboutUsPage aboutUsPage;

    @BeforeAll
    public static void classSetup() {
        aboutUsPage = new AboutUsPage();
    }

    @BeforeEach
    public void beforeTest() {
        aboutUsPage.navigateToAboutUsPage();
    }
    @Test
    public void isAboutUsLogoVisible(){
        Boolean  success =  aboutUsPage.isLogoVisible();
        assertTrue(success);
    }
}
