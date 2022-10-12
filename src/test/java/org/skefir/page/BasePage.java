package org.skefir.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.skefir.data.OptionFilterable;
import org.skefir.elements.CommonElementUtils;
import io.qameta.allure.Allure;

import java.util.Set;

/**
 * Базовый класс страницы, где собраны методы общие для всей тестируемой системы
 * @param <T> - Тип страницы наследника, для поддержки chain вызовов в потомках
 */
public class BasePage<T extends BasePage<T>> {

    /**
     * Класс помошник где собраны элементы страницы, реализация через интерфейсы позволяет использовать блоки
     * компонентов переиспользуемые на сайте
     */
    protected static class Elements implements CommonElementUtils {
    }

    protected Elements elementHelper = new Elements();

    /**
     *  Функция возврата текущей страницы с учетом того что в потомках необходимо возвращать тип потомка
     * @return - объект текущей страницы
     */

    @SuppressWarnings("unchecked")
    protected T getCurrentPage() {
        return (T) this;
    }

    /**
     * Проверка присутствует ли опция фильтра в наборе, сравнения происходит через заголовок или альтернативный заголовок опции
     *
     * @param optionSet - набор опций фильтрации
     * @param labelOption - заголовок опции из ситемы
     * @return true если опция присутствует в наборе
     * @param <O> - параметризованый тип расширяющий тип опции фильтрации
     */
    protected <O extends OptionFilterable> boolean isOptionContains(Set<O> optionSet, String labelOption) {
        return optionSet.stream().anyMatch(e -> e.getTitle().equals(labelOption) || e.getAltTitle().equals(labelOption));
    }


    protected <O extends OptionFilterable> T setFilterCheckboxGroup(SelenideElement filterElement, Set<O> optionSet) {
        elementHelper.getFilterOptions(filterElement).asFixedIterable()
                .forEach(filterOption -> setFilterOptionCheckbox(filterOption,
                        isOptionContains(optionSet, elementHelper.getFilterOptionLabel(filterOption).getText())));
        return getCurrentPage();
    }


    protected boolean isChecked(SelenideElement checkBoxElement) {
        return elementHelper.getFilterCheckBox(checkBoxElement).is(Condition.checked);
    }

    protected void setFilterOption(SelenideElement filterOptionElement) {
        elementHelper.getFilterOptionLabel(filterOptionElement).scrollIntoView("{behavior: \"auto\"}").click();
    }

    protected void setFilterOptionCheckbox(SelenideElement filterOptionElement, boolean value) {
        if (value != isChecked(filterOptionElement)) {
            setFilterOption(filterOptionElement);
        }
    }

    protected T setFilterRadio(SelenideElement filterElement, OptionFilterable option) {
        elementHelper.getFilterOption(filterElement, option).click();
        return getCurrentPage();
    }

    protected static void addAllureTextAttacment(String title, String body) {
        Allure.addAttachment(title, body);
    }
}
