package com.example.bookshelfapp.data

import com.example.bookshelfapp.model.Book
import com.example.bookshelfapp.model.BookList
import com.example.bookshelfapp.network.BooksApiService

interface BooksRepository {
    suspend fun getJazzBooks(): BookList
    suspend fun getBookBySearch(searchValue: String): List<Book>
    suspend fun getBookById(id: String): Book
}

class NetworkBookRepository(
    private val apiService: BooksApiService
) : BooksRepository {
    override suspend fun getJazzBooks(): BookList {
        return apiService.getJazzBooks()
    }

    override suspend fun getBookBySearch(searchValue: String): List<Book> {
        return apiService.getBookBySearch(searchValue)
    }

    override suspend fun getBookById(id: String): Book {
        return apiService.getBookById(id)
    }

}