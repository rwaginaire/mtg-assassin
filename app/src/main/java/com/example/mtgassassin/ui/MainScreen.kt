package com.example.mtgassassin.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.mtgassassin.R
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class AssassinScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    Results(title = R.string.results),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MTGAssassinApp(
    viewModel: AssassinViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AssassinScreen.valueOf(
        backStackEntry?.destination?.route ?: AssassinScreen.Home.name
    )
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(currentScreen.title)) }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AssassinScreen.Home.name
        ) {
            composable(route = AssassinScreen.Home.name) {
                HomeScreen(
                    playersList = viewModel.inputPlayersList,
                    onRemovePlayer = { player -> viewModel.removePlayer(player) },
                    onAddClick = { viewModel.addPlayer() },
                    onInputPlayerChange = { input -> viewModel.updateInputName(input) },
                    inputName = viewModel.inputName,
                    onStartClick = {
                        viewModel.drawTargets()
                        navController.navigate(AssassinScreen.Results.name)
                    },
                    modifier = Modifier.padding(innerPadding)
                )
            }
            composable(route = AssassinScreen.Results.name) {
                DiscoverTargetScreen(
                    player = viewModel.finalPlayers[uiState.currentPlayerCount],
                    onShowClick = { viewModel.revealTarget() },
                    onConfirm = { navigateToNextPlayer(viewModel, navController) },
                    isTargetHidden = uiState.isTargetHidden,
                )
            }
        }
    }
}

private fun navigateToNextPlayer(
    viewModel: AssassinViewModel,
    navController: NavHostController
) {
    if (viewModel.noMoreTargetsToReveal()) {
        viewModel.resetApp()
        navController.popBackStack(AssassinScreen.Home.name, inclusive = false)
    }
    else {
        viewModel.goToNextPlayer()
    }
}