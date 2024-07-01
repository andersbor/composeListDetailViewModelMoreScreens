package com.example.listdetailviewmodelmorescreens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class BooksViewModel : ViewModel() {
    val books = mutableStateListOf(
        Book(1, "Kotlin", "Anders B", 9.95),
        Book(2, "More Kotlin", "Bobby C", 19.95),
        Book(3, "Even more Kotlin", "Bobby C", 4.95),
        Book(4, "Kotlin for the win", "Anders B", 29.95),
        Book(5, "Kotlin for the win", "Anders B", 29.95),
        Book(6, "Kotlin for the win", "Anders B", 29.95),
    )

    //var selectedBook by mutableStateOf<Book?>(null)

    /*val selectBook: (Book) -> Unit = {
        selectedBook = it
    }*/

    fun getBook(id: Int): Book? {
        return books.find { it.id == id }
    }

    val addBook: (Book) -> Unit = {
        books.add(it)
    }

    val removeBook: (Book) -> Unit = {
        books.remove(it)
        /* if (it == selectedBook) {
             selectedBook = null
         }*/
    }
}
