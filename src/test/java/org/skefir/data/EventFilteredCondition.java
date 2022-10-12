package org.skefir.data;

import lombok.Value;

import java.util.Set;

/**
 * класс данных набора фильтров календаря
 */
@Value
public class EventFilteredCondition {
    Set<ImportanceFilterOption> importanceSet;
    DateFilterOptions dateFilterOption;
    Set<Currencies> currenciesSet;
}
