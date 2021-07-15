package com.example.tarabuttest.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity@Parcelize
data class Match(

    @PrimaryKey(autoGenerate = true)
    @SerializedName("uid") val uid: Int ,

    val Team_A: String,
    val Team_B: String,
    val Score: String,
    val link_A: String,
    val link_B: String,
    val Date: String,

): Parcelable
