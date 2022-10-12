package org.skefir.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.skefir.data.DataTableColumn;
import rx.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс для работы со стандартным для системы компонентом таблицы
 * @param <E> - параметр наследник типа набора столбцов таблицы
 */

public class DataTable<E extends DataTableColumn> {

    protected final SelenideElement rootElement;

    protected final String classPrefix;

    protected final Set<E> columns;

    protected Map<E, Integer> columnsNumbers;

    /**
     *
     * @param rootElement - doomelement - содержащий в себе таблицу
     * @param classPrefix - префикс css классов таблицы
     * @param columns - ограниченный набор колонок таблицы
     */
    public DataTable(SelenideElement rootElement, String classPrefix, Set<E> columns) {
        this.rootElement = rootElement;
        this.classPrefix = classPrefix;
        this.columns = columns;
        columnsNumbers = new HashMap<>();
    }

    protected int getColumnNumber(E column) {
        if (!columns.contains(column)) {
            throw new IllegalArgumentException("Illegal column - " + column);
        }

        return columnsNumbers.computeIfAbsent(column, k -> rootElement.$$x(String
                .format(".//div[@class='%s__header']//div[contains(@class,'%s__') " +
                                " and .//text()='%s']/preceding-sibling::div", classPrefix, classPrefix,
                        column.getTitle())).size() + 1);
    }

    public SelenideElement getRowByNumber(int rowNumber) {
        return getRows().get(rowNumber);
    }

    private ElementsCollection getRows() {
        return rootElement.$$x(String.format(".//div[@class='%s__item']"
                , classPrefix));
    }

    public SelenideElement getColumn(SelenideElement rowElement, E column) {
        return rowElement.$(String.format("div[class*='%s__']:nth-child(%d)", classPrefix, getColumnNumber(column)));
    }

    private SelenideElement getBottomArea() {
        return rootElement.$("div[class*='table__bottom']");
    }

    private Observable<SelenideElement> getRowsOnPage(SelenideElement paginatorElement) {
        if (paginatorElement != null) {
            paginatorElement.scrollIntoView(true).click();
        }
        return Observable.from(getRows().asDynamicIterable());
    }

    public Observable<SelenideElement> getRowStream() {
        //получаем колекцию страниц пейджинатора
        ElementsCollection pagerCollection = getBottomArea().$$("a");
        //если страница 1 то колеция пуста добавляем туда 1 элемент страницы
        Observable<SelenideElement> paginationStream = pagerCollection.size() > 0 ?
                Observable.from(pagerCollection.asDynamicIterable()) : Observable.from(rootElement.$$("div[class*='table__bottom']"));
        //конвертируем поток пейджей в поток строк
        return paginationStream.flatMap(this::getRowsOnPage);
    }

    public Map<E, String> extractColumns(SelenideElement rowElement, Set<E> columnsSet) {
        ElementsCollection rowColumns = rowElement.$$(String.format("div[class*='%s__']", classPrefix));
        return columnsSet.stream().collect(Collectors.toMap(k -> k, v -> rowColumns.get(getColumnNumber(v) - 1).getText()));
    }
}
