package com.neopixl.lesmovies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Yvan Moté on 2019-10-17.
 */
@Parcelize
class Movie(val title: String,
            val overview: String,
            @SerializedName("release_date") val releaseDate: String,
            @SerializedName("poster_path") val posterPath: String): Parcelable {
}