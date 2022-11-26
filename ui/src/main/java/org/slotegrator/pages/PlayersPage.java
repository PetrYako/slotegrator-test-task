package org.slotegrator.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.slotegrator.core.annotations.ElementName;

public class PlayersPage extends BasePage{

    @ElementName("Таблица 'PLAYER MANAGEMENT'")
    @FindBy(xpath = ".//div[@id='payment-system-transaction-grid']/table")
    public SelenideElement playersTable;
}
