package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class Home_PageObj {
    private SHAFT.GUI.WebDriver driver;
    private String url = "https://bvtportal.com/login";
    private String title = "Trip Proposal - Login";
    private By userName_textBox = By.id("exampleInputEmail");
    private By password_textBox = By.id("exampleInputPassword");
    private By login_btn = By.xpath("//button[@type=\"submit\"]");
    private By alertArea = By.xpath("//div[@class='alert alert-danger']");

    private String payIconPath = "//td[p[contains(text(), 'value')]]/following-sibling::td/a[@class='pay btn']";
    private String downloadIconPath = "//td[p[contains(text(), 'value')]]/ancestor::tr[1]//td[10]//button[@class='btn dropdown-toggle']";
    private String pdfPath = "//td[p[contains(text(), 'value')]]/ancestor::tr[1]//a[contains(@href,'https://bvtportal.com/pdf?itinerary')]";
    private String bookingDetailsRow = "//td[p[contains(text(), 'value')]]/ancestor::tr";

    public Home_PageObj(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }


    public Home_PageObj navigate() {
        driver.browser().navigateToURL(url);
        return this;
    }


    public Home_PageObj verifyBrowserTitleIsCorrect() {
        driver.verifyThat().browser().title().isEqualTo(title).perform();
        return this;
    }


    public Home_PageObj enterUserNameAndPassword(String userName, String password) {
        driver.element().type(userName_textBox, userName);
        driver.element().type(password_textBox, password);
        driver.element().click(login_btn);
        return this;
    }

    public Home_PageObj clickPayButton(String bookingNumber) {
        driver.element().click(By.xpath(payIconPath.replace("value", bookingNumber)));
        return this;
    }


    public Home_PageObj downloadReceipt(String bookingNumber) {
        driver.element().click(By.xpath(downloadIconPath.replace("value", bookingNumber)));
        driver.element().click(By.xpath(pdfPath.replace("value", bookingNumber)));
        return this;
    }

    public Home_PageObj verifyUserDidNotEnterVendor() {
        driver.element().verifyThat(alertArea).text().contains("Please Check Vendors before downloading.");
        return this;
    }

    public Home_PageObj verfyUserCanDownloadReceipt() {
        driver.element().verifyThat(alertArea).doesNotExist();
        return this;
    }

    public Home_PageObj navigateToBookingDetails(String bookingNumber) {
        driver.element().click(By.xpath(bookingDetailsRow.replace("value", bookingNumber)));
        return this;
    }
}
