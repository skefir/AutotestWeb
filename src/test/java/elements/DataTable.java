package elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataTableColumn;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataTable<E extends DataTableColumn> {

    protected final SelenideElement rootElement;

    protected final String classPrefix;

    protected final Set<E> columns;

    protected Map<E, Integer> columnsNumbers;

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

    private Stream<SelenideElement> getRowsOnPage(SelenideElement paginatorElement) {
        if (paginatorElement != null) {
            paginatorElement.scrollIntoView(true).click();
        }
        return getRows().asDynamicIterable().stream();
    }

    public Stream<SelenideElement> getRowStream() {
        ElementsCollection pagerCollection = getBottomArea().$$("a");
        Stream<SelenideElement> paginationStream = pagerCollection.size() > 0 ?
                pagerCollection.asDynamicIterable().stream() : Stream.of(getBottomArea());
        return paginationStream.flatMap(this::getRowsOnPage);
    }

    public Map<E, String> extractColumns(SelenideElement rowElement, Set<E> columnsSet) {
        ElementsCollection rowColumns = rowElement.$$(String.format("div[class*='%s__']", classPrefix));
        return columnsSet.stream().collect(Collectors.toMap(k -> k, v -> rowColumns.get(getColumnNumber(v) - 1).getText()));
    }
}
