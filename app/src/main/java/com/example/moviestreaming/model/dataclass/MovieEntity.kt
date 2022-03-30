package com.example.moviestreaming.model.dataclass

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites")
data class MovieEntity(
    val category_name: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val link_img: String,
    val name: String,
)