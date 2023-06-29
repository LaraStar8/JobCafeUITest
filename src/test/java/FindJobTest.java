import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import pages.AboutUsPage;
import pages.FindJobPage;
import utils.UseCaseBase;

import static org.junit.jupiter.api.Assertions.*;

public class FindJobTest extends UseCaseBase {

    private static FindJobPage findJobPage;

    @BeforeAll
    public static void classSetup() {
        findJobPage = new FindJobPage();
    }

    @BeforeEach
    public void beforeTest() {
        findJobPage.navigateToFindJobPage();
    }

    @Test
    public void sendLocationsTest() {
        findJobPage.populateLocationField("Toronto, Tel-Aviv, Chicago, New-York");
        String actualLocation = findJobPage.locationElement().getAttribute("value");
        assertEquals("Toronto, Tel-Aviv, Chicago, New-York", actualLocation);
    }

    @Test
    public void changeInFoundItemsByLocationTest() throws InterruptedException {
        String initialResult = findJobPage.findItemsElement().getText();
        findJobPage.populateLocationField("Toronto, Tel-Aviv, Chicago, New-York");
        Thread.sleep(5000);
        String searchResult = findJobPage.findItemsElement().getText();
        assertNotEquals(initialResult, searchResult);

    }

    @ParameterizedTest
    @CsvSource({"Toronto", "Tel-Aviv", "Chicago", "New-York"})
    public void checkIfTheListUpdatedForLocationsTest(String location) throws InterruptedException {
        findJobPage.populateLocationField(location);
        boolean correctList = findJobPage.checkLocationsList(location);
        assertTrue(correctList);
    }

    @Test
    public void sendPositionTest() {
        findJobPage.populatePositionField("QA, Developer, Project Manager");
        String actualPosition = findJobPage.positionElement().getAttribute("value");
        assertEquals("QA, Developer, Project Manager", actualPosition);
    }

    @Test
    public void changeInFoundItemsByPositionTest() throws InterruptedException {
        String initialResult = findJobPage.findItemsElement().getText();
        findJobPage.populatePositionField("QA, Developer, Project Manager");
        Thread.sleep(5000);
        String searchResult = findJobPage.findItemsElement().getText();
        assertNotEquals(initialResult, searchResult);

    }

    @ParameterizedTest
    @CsvSource({"QA", "Developer", "Project Manager"})
    public void checkIfTheListUpdatedForPositionTest(String position) throws InterruptedException {
        findJobPage.populatePositionField(position);
        boolean correctList = findJobPage.checkPositionList(position);
        assertTrue(correctList);
    }

    @Test
    public void sendCompanyTest() {
        findJobPage.populateCompanyField("Apple, Facebook, Google");
        String actualCompany = findJobPage.companyElement().getAttribute("value");
        assertEquals("Apple, Facebook, Google", actualCompany);
    }

    @Test
    public void changeInFoundItemsByCompanyTest() throws InterruptedException {
        String initialResult = findJobPage.findItemsElement().getText();
        findJobPage.populateCompanyField("Apple, Facebook, Google");
        Thread.sleep(5000);
        String searchResult = findJobPage.findItemsElement().getText();
        assertNotEquals(initialResult, searchResult);

    }

    @ParameterizedTest
    @CsvSource({"Apple", "Facebook", "Google"})
    public void checkIfTheListUpdatedForCompanyTest(String company) throws InterruptedException {
        findJobPage.populateCompanyField(company);
        boolean correctList = findJobPage.checkCompanyList(company);
        assertTrue(correctList);
    }

    @Test
    public void combinedSearchTest() throws InterruptedException {
        findJobPage.populateAllFields("manager","USA","Google");
        Thread.sleep(5000);
        boolean correctCompanyList = findJobPage.checkCompanyList("Google");
        assertTrue(correctCompanyList);
        boolean correctPositionList = findJobPage.checkPositionList("manager");
        assertTrue(correctPositionList);
        boolean correctLocationList = findJobPage.checkLocationsList("USA");
        assertTrue(correctLocationList);
    }

    @Test
    public void errorMessageTest() {
        findJobPage.populatePositionField("abracadabra");
        boolean error1Appears = findJobPage.checkError1();
        assertTrue(error1Appears);
        boolean error2Appears = findJobPage.checkError2();
        assertTrue(error2Appears);
    }
    @Test
    public void resetTest() throws InterruptedException {
        findJobPage.populateAllFields("manager","USA","Google");
        Thread.sleep(5000);
        String enteredCompany = findJobPage.companyElement().getAttribute("value");
        String enteredPosition = findJobPage.positionElement().getAttribute("value");
        String enteredLocation = findJobPage.locationElement().getAttribute("value");
        findJobPage.clickResetButton();
        String newCompany = findJobPage.companyElement().getAccessibleName();
        String newPosition = findJobPage.positionElement().getAccessibleName();
        String newLocation = findJobPage.locationElement().getAccessibleName();

        assertNotEquals(newLocation,enteredLocation);
        assertNotEquals(newPosition,enteredPosition);
        assertNotEquals(newCompany,enteredCompany);


        assertEquals("position", newPosition);
        assertEquals("location", newLocation);
        assertEquals("company", newCompany);
    }
}
