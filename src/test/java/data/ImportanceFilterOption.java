package data;

import lombok.Getter;

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

}
