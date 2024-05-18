package com.example.bookshelfapp.model

import kotlinx.serialization.Serializable

@Serializable
data class BookList(
    val kind: String,
    val items: List<Book>
)