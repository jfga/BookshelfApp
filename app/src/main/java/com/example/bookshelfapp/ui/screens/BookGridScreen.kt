package com.example.bookshelfapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookshelfapp.model.Book
import com.example.bookshelfapp.model.BookList

@Composable
fun BookGridScreen(
    books: BookList,
    onBookClick: (Book) -> Unit = {}
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = books.kind, fontSize = 15.sp)
        BookGrid(books = books.items, onBookClick = onBookClick)
    }
}

@Composable
fun BookGrid(
    books: List<Book>,
    onBookClick: (Book) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(16.dp),
        //modifier = Modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(books) {
            BookCard(book = it, onBookClick = onBookClick)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookCard(
    book: Book,
    onBookClick: (Book) -> Unit
) {
    Card(
        modifier = Modifier.clickable {
            onBookClick(book)
        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = book.volumeInfo.title)
            var imgLink = book.volumeInfo.imageLinks.thumbnail.replaceRange(IntRange(0, 3), "https")
            AsyncImage(
                model = imgLink,
                contentDescription = "${book.volumeInfo.title} image",
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentSize()
                    .size(200.dp)
            )
            //Text(text = book.volumeInfo.imageLinks.thumbnail.replaceRange(IntRange(0,3),"https"))
        }
    }

}