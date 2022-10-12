package org.skefir.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.skefir.data.OptionFilterable;

/**
 * стандартные для всех компонентов системы функции работы с элементами
 */
public interface CommonElementUtils {

    default ElementsCollection getFilterOptions(SelenideElement rootFilterElement) {
        return rootFilterElement.$$("li").filter(Condition.visible);
    }


    default SelenideElement getFilterCheckBox(SelenideElement filterOptionElement) {
        return filterOptionElement.$("input[type='checkbox']");
    }

    default SelenideElement getFilterOptionLabel(SelenideElement filterOptionsElement) {
        return filterOptionsElement.$("label");
    }

    default SelenideElement getFilterOption(SelenideElement rootFilterElement,  OptionFilterable option) {
        return rootFilterElement.$x(".//li[.//label[text()='" + option.getTitle() + "']]");
    }
}
