package org.slotegrator.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.slotegrator.core.annotations.ElementName;

public class AdminPanelPage extends BasePage {

    @ElementName(value = "Плашка 'Tickets'", required = true)
    @FindBy(xpath = ".//p[text()='Tickets']/../..")
    public SelenideElement ticketsBlock;

    @ElementName(value = "Плашка 'Withdrawal requests'", required = true)
    @FindBy(xpath = ".//p[text()='Withdrawal requests']/../..")
    public SelenideElement withdrawalReqBlock;


}
