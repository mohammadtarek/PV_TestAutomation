package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class Itinerary_PageObj {
    private SHAFT.GUI.WebDriver driver;
    private final By addTrip_btn = By.xpath("//button[@type=\"submit\"]");

    public Itinerary_PageObj(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public Itinerary_PageObj clickAddTrip() {
        driver.element().click(addTrip_btn);
        return this;
    }
}
