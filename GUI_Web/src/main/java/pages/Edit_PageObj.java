package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Edit_PageObj {
    private SHAFT.GUI.WebDriver driver;
    private By manual_btn = By.xpath("// button[contains(text(),'Manual')]");
    private By country_feild = By.xpath("//select[@id='events_country']");
    private By city_feild = By.xpath("//select[@id='events_city']");
    private By search_BTN = By.xpath("//button[contains(text(),'Search')]");
    private By dateFrom_date = By.xpath("//input[@id='date_range_from']");
    private By dateTo_date = By.xpath("//input[@id='date_range_to']");

    private By addEvent = By.id("dropdownDaysButton-1");
    private By eventDate = By.xpath("//div[@class='dropdown-menu show']//a[@class='dropdown-item'][1]");
    private By editEvent_btn = By.xpath("//i[@class='fa fa-edit text-primary']");
    private By tripTitle = By.id("itiernaryName");
    private By booking_BTN = By.xpath("//span[text()='Bookings']");

    String cityPath = "//select[@id='events_city']//option [text()='value']";
    String countryPath = "//select[@class='form-control']//option[contains(text(),'value')]";
    String bookingNumber;

    public Edit_PageObj(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public Edit_PageObj chooseManualOption() {
        driver.element().click(manual_btn);
        return this;
    }

    public Edit_PageObj selectCountry(String country) {
        driver.element().click(country_feild);
        driver.element().click(By.xpath(countryPath.replace("value", country)));
        return this;
    }

    public Edit_PageObj selectCity(String city) {
        driver.element().clickUsingJavascript(city_feild);
        driver.element().clickUsingJavascript(By.xpath(cityPath.replace("value", city)));
        return this;
    }

    public Edit_PageObj selectEventDate(String min, String max) {
        driver.element().setValueUsingJavaScript(dateFrom_date, min);
        driver.element().setValueUsingJavaScript(dateTo_date, max);

        return this;
    }

    public Edit_PageObj selectEvent() {
        driver.element().clickUsingJavascript(search_BTN);
        driver.element().clickUsingJavascript(addEvent);
        driver.element().clickUsingJavascript(eventDate);
        return this;
    }

    public Edit_PageObj editEvent() {
        driver.element().clickUsingJavascript(editEvent_btn);
        return this;
    }

    public String getBookingNumber() {
        String title = driver.element().getText(tripTitle);
        Pattern pattern = Pattern.compile("Booking\\.No: (\\d+)");
        Matcher matcher = pattern.matcher(title);

        // Extract the booking number
        if (matcher.find()) {
            bookingNumber = matcher.group(1);

        } else {
            System.out.println("Booking number not found in the text.");
        }
        return bookingNumber;
    }

    public Edit_PageObj navigateToBookingPage() {
        driver.element().click(booking_BTN);
        return this;
    }


}

