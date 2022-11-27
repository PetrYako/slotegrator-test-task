package org.slotegrator.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.By;
import org.slotegrator.pages.PlayersPage;

import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlayersSteps {

    private final PlayersPage playersPage = page(PlayersPage.class);
    private List<String> columnValues = new LinkedList<>();

    @Тогда("появляется таблица с игроками")
    public void playerTableLoaded() {
        playersPage.playersTable.shouldBe(Condition.visible);
    }

    @Когда("запоминаем данные колонки {string} в таблице с игроками")
    public void saveDataFromColumn(String column) {
        ElementsCollection valueElements = getDataFromColumn(column);
        columnValues.addAll(valueElements.texts());
    }

    @Когда("нажимаем на колонку с названием {string}")
    public void clickOnColumn(String column) {
        playersPage.playersTable.find(By.xpath(".//thead//th//a[text()=\"" + column + "\"]")).click();
    }

    @Тогда("происходит сортировка таблицы с игроками по колонке {string}")
    public void tableSorted(String column) {
        sleep(5000);
        ElementsCollection valueElements = getDataFromColumn(column);
        assertNotEquals(columnValues, valueElements.texts());
    }

    /**
     * Сбор данных из столбца column
     * @param column - название столбца
     * @return коллекция элементов (строк)
     */
    private ElementsCollection getDataFromColumn(String column) {
        ElementsCollection elements = playersPage.playersTable.findAll(By.xpath(".//thead//th//a"));
        int index = 0;
        for (SelenideElement element : elements) {
            if (element.text().equals(column)) {
                break;
            }
            index++;
        }
        return playersPage.playersTable.findAll(By.xpath(".//tbody//tr//td[" + (index + 2) + "]"));
    }
}
