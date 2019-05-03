package com.tayfuncesur.presentation.mapper

interface Mapper<U ,V> {

    fun mapToView(project : U) :V

}