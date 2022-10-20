package org.skefir.page

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import io.qameta.allure.Allure
import org.skefir.data.OptionFilterable
import org.skefir.elements.CommonUtils

fun Locator.sequence() : Sequence<Pair<Int, Locator>> {
    if (this.count()<1) {
        return emptySequence()
    }
    return generateSequence(Pair(0, this.nth(0)))
    { if (it.first<this.count()-1) Pair(it.first+1, this.nth(it.first+1)) else null}
}

/**
 * Базовый класс страницы, где собраны методы общие для всей тестируемой системы
 * @param <T> - Тип страницы наследника, для поддержки chain вызовов в потомках
</T> */
open class BasePagePW<T : BasePagePW<T>?>(val page: Page) {
    /**
     * Класс помошник где собраны элементы страницы, реализация через интерфейсы позволяет использовать блоки
     * компонентов переиспользуемые на сайте
     */
    protected open inner class Elements : CommonUtils {

    }

    protected open var elementHelper = Elements()

    /**
     * Функция возврата текущей страницы с учетом того что в потомках необходимо возвращать тип потомка
     * @return - объект текущей страницы
     */
    protected val currentPage: T
        get() = this as T

    /**
     * Проверка присутствует ли опция фильтра в наборе, сравнения происходит через заголовок или альтернативный заголовок опции
     *
     * @param optionSet - набор опций фильтрации
     * @param labelOption - заголовок опции из ситемы
     * @return true если опция присутствует в наборе
     * @param <O> - параметризованый тип расширяющий тип опции фильтрации
    </O> */
    protected fun <O : OptionFilterable?> isOptionContains(optionSet: Set<O>, labelOption: String): Boolean {
        return optionSet.stream().anyMatch { e: O -> e!!.title == labelOption || e.altTitle == labelOption }
    }

    protected fun <O : OptionFilterable?> setFilterCheckboxGroup(
        filterElement: Locator,
        optionSet: Set<O>
    ): T {
        elementHelper.getFilterOptions(filterElement).sequence()
            .forEach{ setFilterOptionCheckbox(it.second, isOptionContains(optionSet, elementHelper.getFilterOptionLabel(it.second).textContent()?:""))}
        return currentPage
    }

    protected fun isChecked(checkBoxElement: Locator): Boolean {
        return elementHelper.getFilterCheckBox(checkBoxElement).isChecked
    }

    protected fun setFilterOption(filterOptionElement: Locator) {
        elementHelper.getFilterOptionLabel(filterOptionElement).click()
    }

    protected fun setFilterOptionCheckbox(filterOptionElement: Locator, value: Boolean) {
        if (value != isChecked(filterOptionElement)) {
            setFilterOption(filterOptionElement)
        }
    }

    protected fun setFilterRadio(filterElement: Locator, option: OptionFilterable): T {
        elementHelper.getFilterOption(filterElement, option).click()
        return currentPage
    }

    companion object {
        protected fun addAllureTextAttachment(title: String, body: String) {
            Allure.addAttachment(title, body)
        }
    }
}
