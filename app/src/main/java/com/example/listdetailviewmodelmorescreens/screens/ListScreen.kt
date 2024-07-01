package com.example.listdetailviewmodelmorescreens.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listdetailviewmodelmorescreens.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    books: List<Book>,
    removeBook: (Book) -> Unit = {},
    navigateToDetail: (Int) -> Unit = {},
    navigateToAdd: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Book List") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToAdd() }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        BookListPanel(
            books = books,
            modifier = Modifier.padding(innerPadding),
            onClickItem = { navigateToDetail(it.id) },
            onClickDelete = { removeBook(it) }
        )

    }
}

@Composable
fun BookListPanel(
    books: List<Book>,
    modifier: Modifier = Modifier,
    onClickItem: (Book) -> Unit = {},
    onClickDelete: (Book) -> Unit = {}
) {
    LazyColumn(modifier = modifier) {
        items(books) { book ->
            BookItem(book, onClickItem = onClickItem, onClickDelete = onClickDelete)
        }
    }
}

@Composable
fun BookItem(
    book: Book, modifier: Modifier = Modifier,
    onClickItem: (Book) -> Unit = {},
    onClickDelete: (Book) -> Unit = {}
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(5.dp),
        onClick = { onClickItem(book) }) {
        Row(modifier = Modifier.padding(2.dp)) {
            Text(
                text = book.author,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold
            )
            Text(text = book.title, modifier = Modifier.padding(8.dp), fontStyle = FontStyle.Italic)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { onClickDelete(book) }) {
                Icon(Icons.Outlined.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Preview
@Composable
fun PreviewListScreen() {
    ListScreen(
        books = listOf(
            Book(1, "Title 1", "Author 1", 10.0),
            Book(2, "Title 2", "Author 2", 20.0),
            Book(3, "Title 3", "Author 3", 30.0),
        )
    )
}