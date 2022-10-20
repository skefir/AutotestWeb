package org.skefir.entity

interface DataTableColumn {
    fun getTitle(): String

    fun getColumnByTitle(title: String) : DataTableColumn

}