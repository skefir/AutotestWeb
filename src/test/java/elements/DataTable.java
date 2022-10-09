package elements;

import com.codeborne.selenide.SelenideElement;
import data.DataTableColumn;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataTable<E extends DataTableColumn> {


    protected final SelenideElement rootElement;

    protected final String classPrefix;

    protected final Set<E> columns;

    protected Map<E, Integer> columnsNumbers = null;

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
        return rootElement.$$x(String.format(".//div[@class='%s__item']"
                , classPrefix)).get(rowNumber);
    }

    public SelenideElement getColumn(SelenideElement rowElement, E column) {
        return rowElement.$(String.format("div[class*='%s__']:nth-child(%d)", classPrefix, getColumnNumber(column)));
    }
}
