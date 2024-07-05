package com.example.listdetailviewmodelmorescreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.listdetailviewmodelmorescreens.screens.Add
import com.example.listdetailviewmodelmorescreens.screens.Detail
import com.example.listdetailviewmodelmorescreens.screens.ListScreen
import com.example.listdetailviewmodelmorescreens.ui.theme.ListDetailViewModelMoreScreensTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListDetailViewModelMoreScreensTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel: BooksViewModel = viewModel()

    NavHost(navController = navController, startDestination = NavRoutes.List.route) {
        composable(NavRoutes.List.route) {
            ListScreen(
                viewModel.books,
                removeBook = { book -> viewModel.removeBook(book) },
                navigateToDetail = { bookId -> navController.navigate(NavRoutes.Detail.route + "/$bookId") },
                navigateToAdd = { navController.navigate(NavRoutes.Add.route) },
                sortByTitle = { viewModel.sortBooksByTitle(it) },
                sortByPrice = { viewModel.sortBooksByPrice(it) }
            )
        }
        composable(
            NavRoutes.Detail.route + "/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backstackEntry ->
            val id: Int? = backstackEntry.arguments?.getInt("bookId")
            val book: Book? = if (id == null) null else viewModel.getBook(id)
            Detail(book, navigateBack = { navController.popBackStack() })
        }
        composable(NavRoutes.Add.route) {
            Add(
                addBook = { book -> viewModel.addBook(book) },
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    ListDetailViewModelMoreScreensTheme {
        MainScreen()
    }
}