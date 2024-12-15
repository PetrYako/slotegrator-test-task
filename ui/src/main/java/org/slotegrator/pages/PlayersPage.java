package org.slotegrator.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.slotegrator.core.annotations.ElementName;

public class PlayersPage extends BasePage{

    @ElementName("Таблица 'PLAYER MANAGEMENT'")
    @FindBy(xpath = ".//div[@id='payment-system-transaction-grid']/table")
    public SelenideElement playersTable;

    @ElementName("Кнопка 'Next'")
    @FindBy(xpath = ".//li[not(@class='next hidden')]//a[text()='Next >']")
    public SelenideElement nextBtn;

    @ElementName("Кнопка 'First'")
    @FindBy(xpath = ".//li[not(@class='first hidden')]//a[text()='<< First']")
    public SelenideElement firstBtn;
}
