package com.tayfuncesur.remote.model

import com.google.gson.annotations.SerializedName

class ProjectModel(
    @SerializedName("id") val id: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("stargazers_count") val starCount: Int
)