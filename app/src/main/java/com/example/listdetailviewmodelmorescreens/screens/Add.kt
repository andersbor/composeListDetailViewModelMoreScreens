package com.example.listdetailviewmodelmorescreens.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listdetailviewmodelmorescreens.Book

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(addBook: (Book) -> Unit = {}, navigateBack: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Add a book") })
        }
    ) { innerPadding ->
        AddBookPanel(
            modifier = Modifier.padding(innerPadding),
            addBook = addBook,
            navigateBack = navigateBack,
        )
    }
}

@Composable
fun AddBookPanel(
    modifier: Modifier = Modifier,
    addBook: (Book) -> Unit = {},
    navigateBack: () -> Unit = {},
) {
    var author by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var priceStr by remember { mutableStateOf("") }

    val localAdd = {
        val price = priceStr.toDoubleOrNull() ?: 0.0
        val book = Book(0, title, author, price)
        addBook(book)
        navigateBack
    }
    Column(modifier = modifier) {
        // TODO layout for landscape
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = author,
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            //modifier = Modifier.fillMaxWidth(),
            onValueChange = { author = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text("Author") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { title = it },
            label = { Text("Title") }
        )
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = priceStr,
            onValueChange = { priceStr = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = { Text("Price") }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {

            Button(modifier = Modifier
                .weight(1f)
                .padding(2.dp),
                // TODO is this the right place to declare state change?
                onClick = { navigateBack() }) {
                Text("Cancel")
            }
            Button(modifier = Modifier
                .weight(1f)
                .padding(2.dp),
                onClick = {
                    localAdd()
                    navigateBack()
                }) {
                Text("Add")
            }
        }
    }
}

@Preview
@Composable
fun AddBookPanelPreview() {
    AddBookPanel()
}