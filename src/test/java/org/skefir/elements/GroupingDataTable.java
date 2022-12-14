package org.skefir.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.skefir.data.DataTableColumn;
import lombok.Synchronized;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Класс для работы с компонентом таблицы с группировкой столбцов
 * @param <E> - параметр наследник типа набора столбцов таблицы
 */
public class GroupingDataTable<E extends DataTableColumn> extends DataTable<E> {

    protected AtomicBoolean initFlag = new AtomicBoolean(false);

    /**
     *
     * @param rootElement - doomelement - содержащий в себе таблицу
     * @param classPrefix - префикс css классов таблицы
     * @param columns - ограниченный набор колонок таблицы
     */
    public GroupingDataTable(SelenideElement rootElement, String classPrefix, Set<E> columns) {
        super(rootElement, classPrefix, columns);
    }

    protected int getColumnNumber(E column) {
        if (!columns.contains(column)) {
            throw new IllegalArgumentException("Illegal column - " + column);
        }
        if (!initFlag.get()) {
            calculateColumnNumbers();
        }

        return columnsNumbers.computeIfAbsent(column,
                k -> {
                    throw new IllegalStateException("Can't find key " + column + " in " + getTableHeaders());
                });
    }

    @Synchronized
    private void calculateColumnNumbers() {
        if (!initFlag.get()) {
            List<String> headerList = getTableHeaders().asFixedIterable().stream()
                    .map(element -> element.getOwnText().trim())
                    .collect(Collectors.toList());
            columns.forEach(column -> {
                int index = headerList.indexOf(column.getTitle());
                if (index != -1) {
                    columnsNumbers.put(column, index);
                }
            });
            initFlag.set(true);
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
