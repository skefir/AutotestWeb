package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataTableColumn;
import lombok.Synchronized;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GropingDataTable<E extends DataTableColumn> extends DataTable<E> {

    public GropingDataTable(SelenideElement rootElement, String classPrefix, Set<E> columns) {
        super(rootElement, classPrefix, columns);
        columnsNumbers = null;
    }

    protected int getColumnNumber(E column) {
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
                int index = headerList.indexOf(column.getTitle());
                if (index != -1) {
                    columnsNumbers.put(column, index);
                }
            });
        }
    }

    protected ElementsCollection getTableHeaders() {
        return rootElement
                .$$(String.format(".%s__header .%s__col", classPrefix, classPrefix));
    }

    public SelenideElement getRowByNumber(int rowNumber) {
        return rootElement.$x(String.format(".//div[@class='%s__body']//div[@class='%s__item'][%d]"
                , classPrefix, classPrefix, rowNumber));
    }

    public SelenideElement getColumn(SelenideElement rowElement, E column) {
        return rowElement.$$(String.format(".%s__col", classPrefix)).get(getColumnNumber(column));
    }

}
