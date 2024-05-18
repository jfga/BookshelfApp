package com.example.bookshelfapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookshelfapp.model.Book
import com.example.bookshelfapp.model.BookList
import com.example.bookshelfapp.ui.navigation.Screens

@Composable
fun MainScreen() {
    val viewModel: BooksViewModel = viewModel(factory = BooksViewModel.Factory)
    val navController = rememberNavController()
    BookNavigation(
        viewModel = viewModel,
        navController = navController
    )

    when (viewModel.booksUiState) {
        is BooksUiState.Success -> {
            (viewModel.booksUiState as BooksUiState.Success).books?.let {
                SuccessScreen(
                    it,
                    onBookClick = {
                        viewModel.setSelectedBook(it)
                        navController.navigate(Screens.BookDetails.name)
                    })
            }
        }

        is BooksUiState.Loading -> {
            LoadingScreen()
        }

        is BooksUiState.Error -> {
            ErrorScreen()
        }
    }


}

@Composable
fun SuccessScreen(
    bookList: BookList,
    onBookClick: (Book) -> Unit,
) {
    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Success", fontSize = 20.sp)
            BookGridScreen(books = bookList, onBookClick)
        }

    }
}

@Composable
fun LoadingScreen() {
    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Loading", fontSize = 20.sp)
        }

    }
}

@Composable
fun ErrorScreen() {
    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Error", fontSize = 20.sp)
        }

    }
}

@Composable
fun BookNavigation(
    viewModel: BooksViewModel,
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screens.BooksGrid.name) {
        composable(Screens.BooksGrid.name) {
            viewModel.getBookList()?.let { it1 ->
                SuccessScreen(
                    bookList = it1,
                    onBookClick = {
                        viewModel.setSelectedBook(it)
                        navController.navigate(Screens.BookDetails.name)
                    }
                )
            }
        }

        composable(Screens.BookDetails.name) {
            viewModel.getSelectedBookData()?.let { it1 ->
                BookDetailsScreen(
                    book = it1
                )
            }

        }
    }
}