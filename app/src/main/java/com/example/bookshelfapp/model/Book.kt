package com.example.bookshelfapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    //@SerialName("kind")
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
@SerialName("volumeInfo")
data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val description: String,
    val imageLinks: ImageLinks

)

@Serializable
@SerialName("imageLinks")
data class ImageLinks(
    val thumbnail: String
)