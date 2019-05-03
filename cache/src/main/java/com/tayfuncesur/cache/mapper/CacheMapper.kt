package com.tayfuncesur.cache.mapper

interface CacheMapper<I, O> {

    fun mapFromCache(cache: I): O

    fun mapToCache(cache: O): I

}