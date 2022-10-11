package data;

import lombok.Value;

import java.util.Set;

@Value
public class EventFilteredCondition {
    Set<ImportanceFilterOption> importanceSet;
    DateFilterOptions dateFilterOption;
    Set<Currencies> currenciesSet;
}
