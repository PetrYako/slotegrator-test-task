package org.slotegrator.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.slotegrator.core.annotations.ElementName;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    @ElementName("Меню 'Users'")
    @FindBy(xpath = ".//a[@data-target='#s-menu-users']/..")
    public SelenideElement usersMenu;


    /**
     * Получение элемент по названию. Поиск осуществляется по аннотации @ElementName
     * @param name - значение из @ElementName
     * @return selenideElement
     */
    public SelenideElement getElement(String name) {
        for (Field f: this.getClass().getFields()) {
            if (f.isAnnotationPresent(ElementName.class)) {
                ElementName ename = f.getAnnotation(ElementName.class);
                if (ename.value().equals(name)) {
                    try {
                        return (SelenideElement) f.get(this);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Поиск элементов, которые обязательны к загрузке @ElementName(required = true)
     * @return список SelenideElement
     */
    public List<SelenideElement> getRequiredElements() {
        List<SelenideElement> list = new ArrayList<>();
        for (Field f: this.getClass().getFields()) {
            if (f.isAnnotationPresent(ElementName.class)) {
                ElementName ename = f.getAnnotation(ElementName.class);
                if (ename.required()) {
                    try {
                        list.add((SelenideElement) f.get(this));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return list;
    }
}
