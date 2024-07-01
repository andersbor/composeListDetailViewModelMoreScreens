package com.example.listdetailviewmodelmorescreens

sealed class NavRoutes(val route: String) {
    object List : NavRoutes("list")
    object Detail : NavRoutes("detail")
    object Add : NavRoutes("add")
}