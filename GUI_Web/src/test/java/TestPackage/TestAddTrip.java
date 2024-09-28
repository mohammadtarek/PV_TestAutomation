package TestPackage;

import com.shaft.driver.SHAFT;
import org.testng.annotations.*;
import pages.*;

public class TestAddTrip {
    private SHAFT.GUI.WebDriver driver;
    private SHAFT.TestData.JSON testData;
    String tripName;
    String arrivalDate;
    String agencyNameShortCut;
    String agencyFullName;
    String agentName;
    String passengerContactName;
    String passengerEmail;
    String country;
    String city;
    String tripStartDayDate;
    String tripEndDayDate;
    String numberOfNights;
    String price;
    String bookingNumber;

    @BeforeClass(description = "Setup Test Data.")
    public void beforeClass() {
        //Setup Test Data
        testData = new SHAFT.TestData.JSON("simpleJSON.json");

        // Setup Browser instance
        driver = new SHAFT.GUI.WebDriver();
        new Home_PageObj(driver).navigate();

        // Perform Login
        String userName = testData.getTestData("email");
        String password = testData.getTestData("password");
        new Home_PageObj(driver).enterUserNameAndPassword(userName, password);
    }

    @Test(description = "Check Add Trip.")
    public void addTrip() {
        new Itinerary_PageObj(driver).clickAddTrip();
        tripName = testData.getTestData("tripName");
        numberOfNights = testData.getTestData("numberOfNights");
        arrivalDate = testData.getTestData("arrivalDate");
        agencyNameShortCut = testData.getTestData("agencyNameShortCut");
        agencyFullName = testData.getTestData("agencyFullName");
        agentName = testData.getTestData("agentName");
        passengerContactName = testData.getTestData("passengerContactName");
        passengerEmail = testData.getTestData("passengerEmail");

        new EditHash_PageObj(driver).
                setTripName(tripName).
                setNumOfNights(numberOfNights).
                setArivalDateValue(arrivalDate).
                setAgency(agencyNameShortCut, agencyFullName).
                setAgentName(agentName).
                setPassengerContactName(passengerContactName).
                setPassengerEmail(passengerEmail).
                clickSaveBtn();
    }

    @Test(description = "Check Add Event.", dependsOnMethods = "addTrip")
    public void addEvent() {
        country = testData.getTestData("country");
        city = testData.getTestData("city");
        tripStartDayDate = testData.getTestData("tripStartDayDate");
        tripEndDayDate = testData.getTestData("tripEndDayDate");

        new Edit_PageObj(driver).
                chooseManualOption().
                selectCity(city).
                selectEventDate(tripStartDayDate, tripEndDayDate).
                selectEvent().
                editEvent();
    }

    @Test(description = "Check Edit Event Form.", dependsOnMethods = "addEvent")
    public void editForm() {

        price = testData.getTestData("price");
        int totalPrice = Integer.parseInt(price) * Integer.parseInt(numberOfNights);

        new EventForm_PageObj(driver).
                setEventPrice(price).clickSave();
    }

    @Test(description = "Check Payment", dependsOnMethods = "editForm")
    public void pay() {
        bookingNumber = new Edit_PageObj(driver).getBookingNumber();
        new Edit_PageObj(driver).
                navigateToBookingPage();

        new Home_PageObj(driver).
                clickPayButton(bookingNumber);

        String bankNum = testData.getTestData("bankNum");
        String checkNum = testData.getTestData("checkNum");
        new Payment_PageObj(driver).
                enterBankNum(bankNum).
                enterCheckNum(checkNum).
                clickSaveBtn();
    }

    @Test(description = "Check Customer Can't Download Receipt.", dependsOnMethods = "pay")
    public void checkCanNotDownloadReceipt() {
        new Home_PageObj(driver).
                downloadReceipt(bookingNumber).
                verifyUserDidNotEnterVendor().
                navigateToBookingDetails(bookingNumber);
    }

    @Test(description = "Customer Add Vendor Name", dependsOnMethods = "checkCanNotDownloadReceipt")
    public void addVendor() {
        new Edit_PageObj(driver).editEvent();

        String vendorAutoForComplete = testData.getTestData("vendorAutoForComplete");
        String vendorFullName = testData.getTestData("vendorFullName");
        new EventForm_PageObj(driver).setVendor(vendorAutoForComplete, vendorFullName).clickSave();
        new Edit_PageObj(driver).navigateToBookingPage();
    }

    @Test(description = "Check Customer Can Download Receipt", dependsOnMethods = "addVendor")
    public void checkCanDownloadReceipt() throws InterruptedException {
        new Home_PageObj(driver).
                downloadReceipt(bookingNumber).verfyUserCanDownloadReceipt();
    }

    @AfterClass(description = "Teardown Browser instance.")
    public void afterMethod() {
        driver.quit();
    }
}

