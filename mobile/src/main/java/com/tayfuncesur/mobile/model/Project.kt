package com.tayfuncesur.mobile.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Project(
    val id: String, val fullName: String,
    val starCount: Int, var isBookmarked: Boolean
) : Parcelable