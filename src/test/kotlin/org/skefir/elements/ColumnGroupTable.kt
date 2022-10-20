package org.skefir.elements

import com.microsoft.playwright.Locator
import lombok.Synchronized
import org.skefir.entity.DataTableColumn
import org.skefir.page.sequence
import java.util.concurrent.atomic.AtomicBoolean

class ColumnGroupTable<E : DataTableColumn>(rootElement: Locator, classPrefix: String, columns: Set<E>) :
    DataTablePW<E>(rootElement, classPrefix, columns) {
    protected var initFlag = AtomicBoolean(false)

    override fun getColumnNumber(column: E): Int {
        require(columns.contains(column)) { "Illegal column - $column" }
        if (!initFlag.get()) {
            calculateColumnNumbers()
        }
        return columnsNumbers.computeIfAbsent(
            column
        ) { k: E -> throw IllegalStateException("Can't find key $column in $columns") }
    }

    protected fun getTableHeaders(): Locator {
        return rootElement.locator(".${classPrefix}__header .${classPrefix}__col")
    }

    @Synchronized
    private fun calculateColumnNumbers() {
        if (!initFlag.get()) {
            columnsNumbers = getTableHeaders().sequence()
                .map { (i, l)
                    ->
                    @Suppress("UNCHECKED_CAST")
                    columns.first().getColumnByTitle(l.textContent().trim()) as E to i
                }.toMap().toMutableMap()
            initFlag.set(true)
        }
    }

    override fun getRowByNumber(rowNumber: Int): Locator {
        return rootElement.locator("div.${classPrefix}__body div.${classPrefix}__item:nth-child(${rowNumber})")
    }

    override fun getColumn(rowElement: Locator, column: E): Locator {
        return rowElement.locator(".${classPrefix}__col:nth-child(${getColumnNumber(column)})")
    }
}