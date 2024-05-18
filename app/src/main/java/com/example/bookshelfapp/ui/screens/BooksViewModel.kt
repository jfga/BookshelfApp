package com.example.bookshelfapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelfapp.BookApplication
import com.example.bookshelfapp.data.BooksRepository
import com.example.bookshelfapp.model.Book
import com.example.bookshelfapp.model.BookList
import kotlinx.coroutines.launch

sealed interface BooksUiState {
    data class Success(
        val books: BookList? = null,
        val selectedBook: Book? = null
    ) : BooksUiState
    object Loading : BooksUiState
    object Error : BooksUiState
}

class BooksViewModel(
    private val repository: BooksRepository
) : ViewModel() {

    var booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
        private set

    init {
        getJazzBooks()
    }

    fun getBookList(): BookList? {
        return when(booksUiState){
            is BooksUiState.Success ->{
                (booksUiState as BooksUiState.Success).books
            }

            is BooksUiState.Loading -> {
                null
            }

            is BooksUiState.Error -> {
                null
            }
        }
    }

    fun getSelectedBookData(): Book? {
        return when(booksUiState){
            is BooksUiState.Success ->{
                (booksUiState as BooksUiState.Success).selectedBook
            }

            is BooksUiState.Loading -> {
                null
            }

            is BooksUiState.Error -> {
                null
            }
        }
    }

    fun setSelectedBook(book: Book){
        booksUiState = BooksUiState.Success(books = null, selectedBook = book)
    }

    fun getJazzBooks() {
        viewModelScope.launch {
            booksUiState = BooksUiState.Loading
            booksUiState = BooksUiState.Success(repository.getJazzBooks())

//            booksUiState = try {
//                BooksUiState.Success(repository.getJazzBooks())
//            } catch (e: Error) {
//                BooksUiState.Error
//            } catch (e: Exception) {
//                BooksUiState.Error
//            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BookApplication)
                val booksRepository = application.container.booksRepository
                BooksViewModel(repository = booksRepository)
            }
        }
    }
}

