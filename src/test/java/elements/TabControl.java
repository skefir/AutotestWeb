package elements;

import com.codeborne.selenide.SelenideElement;
import data.ControlTabEntity;

public class TabControl<T extends ControlTabEntity> {

    private final SelenideElement rootElement;

    public TabControl(SelenideElement rootElement) {
        this.rootElement = rootElement;
    }

    public void enterToTab(T tab) {
        rootElement.$x(".//li[text()=" + tab.getTitle() + "]").click();
    }

}
