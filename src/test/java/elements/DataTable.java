package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataTableColumn;
import lombok.Synchronized;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DataTable<E extends DataTableColumn> {

    private final SelenideElement rootElement;

    private final Set<E> columns;

    private Map<E, Integer> columnsNumbers = null;

    public DataTable(SelenideElement rootElement, Set<E> columns) {
        this.rootElement = rootElement;
        this.columns = columns;
    }

    private int getCollumnNumber(E column) {
        if (!columns.contains(column)) {
            throw new IllegalArgumentException("Illegal column - " + column);
        }
        if (columnsNumbers == null) {
            calculateColumnNumbers();
        }

        return columnsNumbers.computeIfAbsent(column,
                k -> {
                    throw new IllegalStateException("Can't find key " + column + " in " + getTableHeaders());
                });
    }

    @Synchronized
    private void calculateColumnNumbers() {
        if (columnsNumbers == null) {
            columnsNumbers = new HashMap<>();
            List<String> headerList = getTableHeaders().asFixedIterable()
                    .stream().map(element -> element.getOwnText().trim()).collect(Collectors.toList());
            columns.forEach(column -> {
                if (headerList.indexOf(column.getTitle()) != -1) {
                    columnsNumbers.put(column, headerList.indexOf(column.getTitle()));
                }
            });
        }
    }

    private ElementsCollection getTableHeaders() {
        return rootElement
                .$$(".ec-table__header .ec-table__col");
    }

    public SelenideElement getRowByNumber(int rowNumber) {
        return rootElement.$x(".//div[@class='ec-table__body']//div[@class='ec-table__item'][" + rowNumber + "]");
    }

    public SelenideElement getColumn(SelenideElement rowElement, E column) {
        return rowElement.$$(".ec-table__col").get(getCollumnNumber(column));
    }
}
