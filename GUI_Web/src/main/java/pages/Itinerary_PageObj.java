package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class Itinerary_PageObj {
    private SHAFT.GUI.WebDriver driver;
    private final By addTrip_btn = By.xpath("//button[@type=\"submit\"]");
    private final By addItinerary_span = By.xpath("//span[@id='itiernaryName']");

    public Itinerary_PageObj(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public Itinerary_PageObj clickAddTrip() {
        driver.element().click(addTrip_btn);
        driver.element().click(addItinerary_span);
        return this;
    }
    public void clickAddTrip2() {
        driver.element().click(addTrip_btn);
        driver.element().click(addItinerary_span);

    }
}
