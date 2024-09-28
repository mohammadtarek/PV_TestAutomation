package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class Payment_PageObj {
    private SHAFT.GUI.WebDriver driver;

    public Payment_PageObj(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    private final By bankNum_input = By.xpath("//input[@placeholder='BankNumber']");
    private final By checkNum_input = By.xpath("//input[@placeholder='CheckNumber']");
    private final By save_btn = By.xpath("//button[@type='submit']");

    public Payment_PageObj enterBankNum(String bankNum) {
        driver.element().type(bankNum_input, bankNum);
        return this;
    }

    public Payment_PageObj enterCheckNum(String checkNum) {
        driver.element().type(checkNum_input, checkNum);
        return this;
    }

    public Payment_PageObj clickSaveBtn() {
        driver.element().click(save_btn);
        return this;
    }
}
