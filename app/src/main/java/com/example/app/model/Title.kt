package com.example.app.model

import com.google.gson.annotations.SerializedName

data class Title(
    val text : String,
    // category detail 영역에서는 iconUrl이 필요없기 때문에 nullable로 처리
    @SerializedName("icon_url")
    val iconUrl : String?
)
