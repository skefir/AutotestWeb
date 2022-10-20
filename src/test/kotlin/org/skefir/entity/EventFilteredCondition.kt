package org.skefir.entity

import org.skefir.data.Currencies
import org.skefir.data.DateFilterOptions
import org.skefir.data.ImportanceFilterOption

data class EventFilteredCondition(
    var importanceSet: Set<ImportanceFilterOption>,
    var dateFilterOption: DateFilterOptions,
    var currenciesSet: Set<Currencies>)
