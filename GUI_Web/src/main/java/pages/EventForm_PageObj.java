package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.asserts.SoftAssert;

public class EventForm_PageObj {
    private SHAFT.GUI.WebDriver driver;
    private final By amount = By.id("inputAmount");
    private final By perRoomPerPerson = By.xpath("//input[@name='people' and @value='per_room_night']");
    private final By save_BTN = By.xpath("//button[contains(text(),'Save')]");
    private final By vendorTextBox = By.xpath("//input[@id='vendorName']");
    String vendorPath = "//a[text()='value']";

    public EventForm_PageObj(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public EventForm_PageObj setEventPrice(String price) {
        driver.element().type(amount, price);
        driver.element().click(perRoomPerPerson);
        //to get the total price as it's written using javaScript, so I should get the value using javaScript.


        return this;
    }

    public EventForm_PageObj checkTotalAmount(int expectedTotalPrice) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String displayedTotalValue = (String) js.executeScript("return document.getElementById('amountInput').value;");
        SoftAssert asserts = new SoftAssert();
        System.out.println("Actual :" + displayedTotalValue + "expected: " + expectedTotalPrice);
        asserts.assertEquals(Integer.parseInt(displayedTotalValue), expectedTotalPrice, "Incorrect Total Price!!");
        return this;
    }

    public EventForm_PageObj clickSave() {
        driver.element().click(save_BTN);
        return this;
    }

    public EventForm_PageObj setVendor(String vendorForAutoComplete, String vendorFullName) {
        driver.element().type(vendorTextBox, vendorForAutoComplete);
        driver.element().click(By.xpath(vendorPath.replace("value", vendorFullName)));
        return this;
    }
}
