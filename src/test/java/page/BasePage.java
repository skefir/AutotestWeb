package page;

import com.codeborne.selenide.SelenideElement;
import data.OptionFilterable;
import elements.CommonElementUtils;

import java.util.Set;

public class BasePage<T extends BasePage<T>> {

    protected static class Elements implements CommonElementUtils {
    }

    protected Elements elementHelper = new Elements();

    @SuppressWarnings("unchecked")
    protected T getCurrentPage() {
        return (T) this;
    }

    private <O extends OptionFilterable> boolean isOptionContains(Set<O> optionSet, String labelOption) {
        return optionSet.stream().anyMatch(e -> e.getTitle().equals(labelOption));
    }

    protected <O extends OptionFilterable> T setFilterCheckboxGroup(SelenideElement filterElement, Set<O> optionSet) {
        elementHelper.getFilterOptions(filterElement).asFixedIterable()
                .forEach(filterOption -> elementHelper.setCheckBox(elementHelper.getFilterCheckBox(filterOption),
                        isOptionContains(optionSet, elementHelper.getFilterOptionLabel(filterOption))));
        return getCurrentPage();
    }

    protected T setFilterRadio(SelenideElement filterElement, OptionFilterable option) {
        elementHelper.getFilterOption(filterElement, option).click();
        return getCurrentPage();
    }
}
