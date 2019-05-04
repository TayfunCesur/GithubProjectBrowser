package com.tayfuncesur.mobile.mapper

interface ViewMapper<V, P> {

    fun mapToView(view: V): P

}