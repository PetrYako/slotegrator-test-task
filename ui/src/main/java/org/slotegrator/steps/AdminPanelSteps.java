package org.slotegrator.steps;

import com.codeborne.selenide.Condition;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import org.openqa.selenium.By;
import org.slotegrator.pages.AdminPanelPage;

import static com.codeborne.selenide.Selenide.page;

public class AdminPanelSteps {

    private final AdminPanelPage adminPanelPage = page(AdminPanelPage.class);

    @Когда("загружается админ-панель")
    public void adminPanelIsLoaded() {
        adminPanelPage.getRequiredElements().forEach(el -> el.shouldBe(Condition.visible));
    }

    @И("переходим к списку игроков")
    public void goToPlayers() {
        adminPanelPage.usersMenu.click();
        adminPanelPage.usersMenu.find(By.xpath(".//*[text()='Players']")).click();
    }
}
