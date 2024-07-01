package com.example.listdetailviewmodelmorescreens.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.listdetailviewmodelmorescreens.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Detail(book: Book?, navigateBack: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Book Details") })
        }
    ) { innerPadding ->
        BookDetailsPanel(
            book = book,
            modifier = Modifier.padding(innerPadding),
            navigateBack = navigateBack
        )

    }
}

@Composable
fun BookDetailsPanel(book: Book?, modifier: Modifier = Modifier, navigateBack: () -> Unit = {}) {
    Column(modifier = modifier) {
        if (book != null) {
            Column {
                Text(text = book.title)
                Text(text = book.author)
                Text(text = book.price.toString())
            }
        } else {
            Text(text = "No book selected", modifier = modifier)
        }
        Button(onClick = navigateBack) {
            Text("Back")
        }
    }
}

@Preview
@Composable
fun PreviewBookDetailsPanel() {
    Detail(Book(id = 0, title = "My Book", author = "Me", price = 10.0))
}