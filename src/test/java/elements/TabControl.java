package elements;

import com.codeborne.selenide.SelenideElement;
import data.ControlTabEntity;

public class TabControl<T extends ControlTabEntity> {

    private final SelenideElement rootElement;

    public TabControl(SelenideElement rootElement) {
        this.rootElement = rootElement;
    }

    public SelenideElement getTab(T tab) {
        return rootElement.$x(".//li[text()='" + tab.getTitle() + "']");
    }

}
