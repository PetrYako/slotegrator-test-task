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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayersSteps {

    private final PlayersPage playersPage = page(PlayersPage.class);
    private ThreadLocal<LinkedList<String>> columnValues = ThreadLocal.withInitial(LinkedList::new);

    @Тогда("появляется таблица с игроками")
    public void playerTableLoaded() {
        playersPage.playersTable.shouldBe(Condition.visible);
    }

    @Когда("запоминаем данные колонки {string} в таблице с игроками")
    public void saveDataFromColumn(String column) {
        List<String> values = getDataFromAllTable(column);
        columnValues.get().addAll(values);
    }

    @Когда("нажимаем на колонку с названием {string}")
    public void clickOnColumn(String column) {
        playersPage.playersTable.find(By.xpath(".//thead//th//a[text()=\"" + column + "\"]")).click();
    }

    @Тогда("происходит сортировка таблицы с игроками по колонке {string}")
    public void tableSorted(String column) {
        List<String> sortedColumns = (List<String>) columnValues.get().stream().sorted();
        playersPage.firstBtn.click();
        List<String> currentColumns = new LinkedList<>(getDataFromAllTable(column));

        assertEquals(sortedColumns, currentColumns);
    }

    /**
     * Проходимся по всей таблице, по всем страницам и собираем данные
     * @param column - название колонки
     * @return данные колонки
     */
    private List<String> getDataFromAllTable(String column) {
        List<String> values = new LinkedList<>();
        int index = getColumnIndex(column);
        do {
            ElementsCollection valueElements = getDataFromColumn(index);
            values.addAll(valueElements.texts());
            playersPage.nextBtn.click();
            sleep(500);
        } while (playersPage.nextBtn.isDisplayed());
        return values;
    }

    /**
     * Сбор данных из столбца column
     * @param index - индекс столбца
     * @return коллекция элементов (строк)
     */
    private ElementsCollection getDataFromColumn(int index) {
        return playersPage.playersTable.findAll(By.xpath(".//tbody//tr//td[" + (index + 1) + "]"));
    }

    /**
     * Вернуть индекс колонки
     * @return индекс
     */
    private int getColumnIndex(String columnName) {
        ElementsCollection elements = playersPage.playersTable.findAll(By.xpath(".//thead//th"));
        int index = 0;
        for (SelenideElement element : elements) {
            if (element.text().equals(columnName)) {
                break;
            }
            index++;
        }
        return index;
    }
}
