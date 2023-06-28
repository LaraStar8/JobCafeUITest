package pages;

import consts.Consts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FindJobPage extends BasePage {
    private static final String LOCATION = "//input[@name='location']";
    private static final String POSITION = "//input[@name='position']";
    private static final String COMPANY = "//input[@name='company']";
    private static final String ERROR_MESSAGE_1 = "//div[@class='error-indicator']//descendant::span[text()='No results found!']";
    private static final String ERROR_MESSAGE_2 = "//div[@class='error-indicator']//descendant::span[text()='Please try different search criteria']";
    private static final String SEARCH = "//button[text()='search']";
    private static final String RESET = "//button[text()='reset']";
    private static final String FOUND_ITEMS = "//div[@class='container']//descendant::span[@style='margin-left: 3em;']";
    private static final String JOBS_LIST = "//ul[@class='entry-meta']//following-sibling::li[@class='entry-content']";


    public String getFindJobURL() {
        return getCurrentURL();
    }

    public void navigateToFindJobPage() {
        webDriver.get(Consts.MAIN_URL);
        clickElement("//*[@name='Find Job']");
    }

    public void populateLocationField(String location) {
        sendKeyToElement(LOCATION, location);
        clickElement(SEARCH);
    }
    public void populatePositionField(String position){
        sendKeyToElement(POSITION,position);
        clickElement(SEARCH);
    }
    public void populateCompanyField(String company){
        sendKeyToElement(COMPANY,company);
        clickElement(SEARCH);
    }
    public void populateAllFields(String position, String location, String company){
        sendKeyToElement(COMPANY,company);
        sendKeyToElement(LOCATION,location);
        sendKeyToElement(POSITION,position);
        clickElement(SEARCH);
    }

    public WebElement locationElement() {
        return findElementByXpath(LOCATION);
    }
    public WebElement positionElement(){
        return findElementByXpath(POSITION);
    }
    public WebElement companyElement(){
        return findElementByXpath(COMPANY);
    }
    public WebElement findItemsElement(){
        return findElementByXpath(FOUND_ITEMS);
    }
    public boolean checkLocationsList(String enteredLocation) throws InterruptedException {
        return checkTheList(JOBS_LIST, enteredLocation);
    }
    public boolean checkPositionList(String enteredPosition) throws InterruptedException {
        return checkTheList(JOBS_LIST, enteredPosition);
    }
    public boolean checkCompanyList(String enteredCompany) throws InterruptedException {
        return checkTheList(JOBS_LIST, enteredCompany);
    }
    public boolean checkError1(){
        return isElementExist(ERROR_MESSAGE_1);
    }
    public boolean checkError2(){
        return isElementExist(ERROR_MESSAGE_2);
    }
    public void clickResetButton(){
        clickElement(RESET);
    }

}



