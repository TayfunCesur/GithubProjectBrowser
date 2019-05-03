package com.tayfuncesur.cache.mapper

import com.tayfuncesur.cache.model.CachedProject

class CachedProjectMapper : CacheMapper<CachedProject, String> {

    override fun mapFromCache(cache: CachedProject): String {
        return cache.id
    }

    override fun mapToCache(cache: String): CachedProject {
        return CachedProject(cache, true)
    }
}