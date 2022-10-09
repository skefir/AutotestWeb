package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.OptionFilterable;

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
