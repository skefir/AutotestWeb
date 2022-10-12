package org.skefir.data;

import lombok.Getter;

/**
 * фильтр важности в календаре
 */
public enum ImportanceFilterOption implements OptionFilterable {
    HOLIDAYS("Holidays"),
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    @Getter
    final String title;

    ImportanceFilterOption(String title) {
        this.title = title;
    }

    @Override
    public String getAltTitle() {
        return getTitle().toUpperCase();
    }

    @Override
    public String toString() {
        return name() + "-" +  getTitle() + "(" + getAltTitle() + ")";
    }

}
