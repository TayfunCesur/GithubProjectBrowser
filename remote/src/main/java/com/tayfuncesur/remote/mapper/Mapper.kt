package com.tayfuncesur.remote.mapper

interface Mapper<M, E> {

    fun mapFromModel(model : M) :E

}