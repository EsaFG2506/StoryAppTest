package com.dicoding.storyapptest.data.response

import com.google.gson.annotations.SerializedName

data class UploadFileResponse (
    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)
