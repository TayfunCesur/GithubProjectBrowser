package com.tayfuncesur.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.tayfuncesur.cache.Constants

@Entity(tableName = Constants.tableName)
class CachedProject(@PrimaryKey var id: String, var isBookmarked: Boolean)