package org.skefir.data

import org.skefir.data.Currencies

data class EventFilteredCondition(
    var importanceSet: Set<ImportanceFilterOption>,
    var dateFilterOption: DateFilterOptions,
    var currenciesSet: Set<Currencies>)
