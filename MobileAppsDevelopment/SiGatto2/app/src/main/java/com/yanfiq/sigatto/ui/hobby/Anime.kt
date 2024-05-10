package com.yanfiq.sigatto.ui.hobby

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val name: String,
    val img: Int,
    val shortDesc: String,
    val longDesc: String
) : Parcelable