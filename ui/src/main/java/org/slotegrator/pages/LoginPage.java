package org.slotegrator.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.slotegrator.core.annotations.ElementName;

public class LoginPage extends BasePage {

    @ElementName(value = "Поле ввода 'Login'", required = true)
    @FindBy(xpath = ".//input[@id = 'UserLogin_username']")
    public SelenideElement loginInput;

    @ElementName(value = "Поле ввода 'Password'", required = true)
    @FindBy(xpath = ".//input[@id = 'UserLogin_password']")
    public SelenideElement passwordInput;

    @ElementName(value = "Кнопка 'Sign in'", required = true)
    @FindBy(xpath = ".//input[@value = 'Sign in']")
    public SelenideElement signInBtn;
}
