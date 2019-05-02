package com.tayfuncesur.data.mapper

interface Mapper<E, D> {

    fun mapFromEntity(entity: E): D

    fun mapToEntity(domain: D): E

}