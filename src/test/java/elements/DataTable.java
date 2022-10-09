package elements;

import com.codeborne.selenide.SelenideElement;
import data.DataTableColumn;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataTable<E extends DataTableColumn> {

    private final SelenideElement rootElement;

    private final Set<E> columns;

    private final Map<E, Integer> columnsNumbers;

    public DataTable(SelenideElement rootElement, Set<E> columns) {
        this.rootElement = rootElement;
        this.columns = columns;
        columnsNumbers = new HashMap<>();
    }

    private int getCollumnNumber(E column) {
        if (!columns.contains(column)) {
            throw new IllegalArgumentException("Illegal column - " + column);
        }
        return columnsNumbers.computeIfAbsent(column,
                k -> rootElement.$$x(".//div[@class='ec-table__header']//div[contains(@class,'ec-table__col') " +
                        "and contains(text()[1],'" + column.getTitle()
                        + "')]//preceding-sibling::div[contains(@class,'ec-table__col')]").size() + 1);
    }

    public SelenideElement getRowByNumber(int rowNumber) {
        return rootElement.$x(".//div[@class='ec-table__body']//div[@class='ec-table__item'][" + rowNumber + "]");
    }

    public SelenideElement getColumn(SelenideElement rowElement, E column) {
        return rowElement.$x(".//div[contains(@class,'ec-table__col')][" + getCollumnNumber(column) + "]");
    }
}
