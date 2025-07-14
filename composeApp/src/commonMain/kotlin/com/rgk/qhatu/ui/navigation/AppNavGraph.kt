package com.rgk.qhatu.ui.navigation
//@Composable
//fun AppNavGraph_() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = RouteNavigation.Splash.src) {
//
//        composable(RouteNavigation.Search.src) {
//            SearchScreen(navController)
//        }
//
//        composable(RouteNavigation.Setting.src) {
//            SettingsScreen(navController)
//        }
//        composable(RouteNavigation.Sale.src) {
//            SaleScreen(navController)
//        }
//
//        composable(
//            route = RouteNavigation.SearchProvider.src,
//            arguments = listOf(navArgument(RouteNavigation.SearchProvider.Args.QUERY) { type = NavType.StringType })
//        ) { backstackEntry ->
//            val query = checkNotNull(backstackEntry.arguments?.getString(RouteNavigation.SearchProvider.Args.QUERY))
//            SearchProviderScreen(
//                viewModel = koinViewModel(parameters = { parametersOf(query) }),
//                navController = navController
//            )
//        }
//    }
//}
