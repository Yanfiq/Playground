package com.yanfiq.sigatto.ui.hobby

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val name: String,
    val img: Int,
    val desc: String
) : Parcelable