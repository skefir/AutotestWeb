package data;

import lombok.Getter;

public enum DateFilterOptions implements OptionFilterable {
    CURRENT_WEEK(  "Current week"),
    PREVIOUS_WEEK( "Previous week"),
    NEXT_WEEK(     "Next week"),
    CURRENT_MONTH( "Current month"),
    PREVIOUS_MONTH("Previous month"),
    NEXT_MONTH(    "Next month");
    @Getter
    final String title;

    DateFilterOptions(String title) {
        this.title = title;
    }
}
