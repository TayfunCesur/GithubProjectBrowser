package com.tayfuncesur.cache.dao

import android.arch.persistence.room.*
import com.tayfuncesur.cache.Constants
import com.tayfuncesur.cache.model.CachedProject
import io.reactivex.Flowable

@Dao
interface CachedProjectsDao {

    @Query(Constants.selectQuery)
    fun getBookmarkedProjects(): Flowable<List<CachedProject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setBookmarkedProject(cachedProject: CachedProject)

    @Query("DELETE FROM ${Constants.tableName} where id = :projectId")
    fun setUnBookmarkedProject(projectId: String)

}