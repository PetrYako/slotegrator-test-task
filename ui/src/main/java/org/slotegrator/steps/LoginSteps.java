package org.slotegrator.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Когда;
import org.aeonbits.owner.ConfigFactory;
import org.slotegrator.core.driver.Driver;
import org.slotegrator.core.props.Properties;
import org.slotegrator.pages.LoginPage;

public class LoginSteps {

    private final LoginPage loginPage = Selenide.page(LoginPage.class);
    private final Properties properties = ConfigFactory.create(Properties.class);

    @Когда("авторизовываемся в админке пользователем {string}")
    public void authorize(String username) {
        Driver.openPage(properties.getUrl());
        loginPage.getRequiredElements().forEach(el -> {
            el.shouldBe(Condition.visible);
        });
        loginPage.loginInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(properties.adminPass());
        loginPage.signInBtn.click();
    }
}
