package com.example.bookshelfapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bookshelfapp.model.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailsScreen(
    book: Book
) {
    Scaffold {
        Column(Modifier.padding(it)) {
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
            Text(text = book.volumeInfo.description)
        }
    }
}