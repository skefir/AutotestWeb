package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.OptionFilterable;
import elements.CommonElementUtils;
import io.qameta.allure.Allure;

import java.util.Set;

public class BasePage<T extends BasePage<T>> {

    protected static class Elements implements CommonElementUtils {
    }

    protected Elements elementHelper = new Elements();

    @SuppressWarnings("unchecked")
    protected T getCurrentPage() {
        return (T) this;
    }

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

    protected static void addAllureDescription(String title, String info) {
        Allure.addAttachment(title, info);
    }
}
