package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class EditHash_PageObj {
    private SHAFT.GUI.WebDriver driver;
    private By tripName_textBox = By.xpath("//div[@class='col-12 col-md-8']//input[@id='tripName']");
    private By precentage_title = By.xpath("");
    private By numOfNights_textBox = By.id("NumOfDays");
    private By arrivalDate_date = By.id("travelDay");
    private By agency_textBox = By.xpath("//input[@name='agency_name' and @id ='agencyName']");
    private By agentName_DDL = By.xpath("//Select[@id='agent_dropdown']");
    private By passengerContactName_textBox = By.id("first_name");
    private By passengerEmail = By.id("summernote");
    private By save_btn = By.xpath("//button[ @class='btn btn-primary d-block mx-auto px-5']");
    private String agencyFullName_DDL = "//li//a[@class='dropdown-item' and contains(text(),'value')]";
    private String agentName = "//option[text()='value']";

    public EditHash_PageObj(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public EditHash_PageObj setTripName(String tripName) {
        driver.element().type(tripName_textBox, tripName);
        return this;
    }

    public EditHash_PageObj setNumOfNights(String num) {
        driver.element().type(numOfNights_textBox, num);
        return this;
    }

    public EditHash_PageObj setArivalDateValue(String date) {
        driver.element().setValueUsingJavaScript(arrivalDate_date, date);
        return this;
    }

    public EditHash_PageObj setAgency(String agentNameShortCut, String completeAgentName) {
        driver.element().type(agency_textBox, agentNameShortCut);
        driver.element().click(By.xpath(agencyFullName_DDL.replace("value", completeAgentName)));
        return this;
    }

    public EditHash_PageObj setAgentName(String name) {
        driver.element().click(agentName_DDL);
        driver.element().click(By.xpath(agentName.replace("value", name)));
        return this;
    }

    public EditHash_PageObj setPassengerContactName(String passengerContactName) {
        driver.element().type(passengerContactName_textBox, passengerContactName);
        return this;
    }

    public EditHash_PageObj setPassengerEmail(String email) {
        driver.element().type(passengerEmail, email);
        return this;
    }

    public EditHash_PageObj clickSaveBtn() {
        driver.element().click(save_btn);
        return this;
    }

}
