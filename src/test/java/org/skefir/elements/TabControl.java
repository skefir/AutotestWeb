package org.skefir.elements;

import com.codeborne.selenide.SelenideElement;
import org.skefir.data.ControlTabEntity;

/**
 * Класс работы с стандартным для системы компонентом TabControl
 * @param <T> параметризированый тип набора вкладок для конкретного контрола
 */

public class TabControl<T extends ControlTabEntity> {

    private final SelenideElement rootElement;

    public TabControl(SelenideElement rootElement) {
        this.rootElement = rootElement;
    }

    public SelenideElement getTab(T tab) {
        return rootElement.$x(".//li[text()='" + tab.getTitle() + "']");
    }

}
