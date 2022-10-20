package org.skefir.elements

import com.microsoft.playwright.Locator
import org.skefir.data.OptionFilterable

interface CommonUtils {
    fun getFilterOptions(rootFilterElement: Locator): Locator {
        return rootFilterElement.locator("li:visible")
    }

    fun getFilterCheckBox(filterOptionElement: Locator): Locator {
        return filterOptionElement.locator("input[type='checkbox']")
    }

    fun getFilterOptionLabel(filterOptionsElement: Locator): Locator {
        return filterOptionsElement.locator("label")
    }

    fun getFilterOption(rootFilterElement: Locator, option: OptionFilterable): Locator {
        return rootFilterElement.locator("li label:has-text('${option.getTitle()}')")
    }
}