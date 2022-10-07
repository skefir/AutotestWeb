package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.OptionFilterable;

public interface CommonElementUtils {

    default ElementsCollection getFilterOptions(SelenideElement rootFilterElement) {
        return rootFilterElement.$$("li");
    }

    default boolean isChecked(SelenideElement checkBoxElement) {
        return getFilterCheckBox(checkBoxElement).is(Condition.checked);
    }

    default SelenideElement getFilterCheckBox(SelenideElement checkBoxElement) {
        return checkBoxElement.$("input[type='checkbox']");
    }

    default String getFilterOptionLabel(SelenideElement filterOptionsElement) {
        return filterOptionsElement.$("label").getText();
    }

    default void setCheckBox(SelenideElement checkBoxElement, boolean value) {
        if (value != isChecked(checkBoxElement)) {
            getFilterCheckBox(checkBoxElement).click();
        }
    }

    default SelenideElement getFilterOption(SelenideElement rootFilterElement,  OptionFilterable option) {
        return rootFilterElement.$x(".//li[.//label[text()='" + option.getTitle() + "']]//input");
    }
}
