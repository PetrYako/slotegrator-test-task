package org.slotegrator.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Когда;
import org.aeonbits.owner.ConfigFactory;
import org.slotegrator.core.props.Properties;
import org.slotegrator.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginSteps {

    private final LoginPage loginPage = Selenide.page(LoginPage.class);
    private final Properties properties = ConfigFactory.create(Properties.class);

    @Когда("авторизовываемся в админке пользователем {string}")
    public void authorize(String username) {
        open(properties.getUrl());
        loginPage.getRequiredElements().forEach(el -> {
            el.shouldBe(Condition.visible);
        });
        loginPage.loginInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(properties.adminPass());
        loginPage.signInBtn.click();
    }
}
