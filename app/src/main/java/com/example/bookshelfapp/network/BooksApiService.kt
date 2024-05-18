package com.example.bookshelfapp.network

import com.example.bookshelfapp.model.Book
import com.example.bookshelfapp.model.BookList
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Path


interface BooksApiService {
    @GET("volumes?q=jazz+history")
    suspend fun getJazzBooks(): BookList

    @GET("volumes?q={searchParam}")
    suspend fun getBookBySearch(@Path("searchParam") searchParam: String): List<Book>

    @GET("volumes/{volume_id}")
    suspend fun getBookById(@Path("volume_id") volumeId: String): Book
}