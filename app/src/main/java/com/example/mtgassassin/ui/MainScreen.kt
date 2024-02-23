package com.example.mtgassassin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import com.example.mtgassassin.R
import com.example.mtgassassin.ui.theme.MTGAssassinTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mtgassassin.data.Player
import com.example.mtgassassin.utils.testPlayersList

@Composable
fun MTGAssassinApp(
) {
    MainScreen()
}

@Composable
fun MainScreen(viewModel: AssassinViewModel = viewModel()) {
    MainScreen(
        playersList = viewModel.playersList,
        onRemovePlayer = { player -> viewModel.removePlayer(player) },
        onAddClick = { viewModel.addPlayer() },
        onInputPlayerChange = { input -> viewModel.updateInputName(input) },
        inputName = viewModel.inputName
    )
}

@Composable
fun MainScreen(
    playersList: List<Player>,
    onRemovePlayer: (Player) -> Unit,
    onAddClick: () -> Unit,
    onInputPlayerChange: (String) -> Unit,
    inputName: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AddPlayerComponent(
            playerName = inputName,
            onNameChange = onInputPlayerChange,
            onAddClick = onAddClick
        )
        PlayersListDisplay(
            playersList = playersList,
            onRemovePlayer = onRemovePlayer,
            modifier = Modifier.weight(1f)
        )
        //Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Text(stringResource(R.string.play))
        }
    }
}

@Composable
fun PlayersListDisplay(
    playersList: List<Player>,
    onRemovePlayer: (Player) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(playersList){ player ->
            PlayerItem(
                playerName = player.name,
                onRemove = { onRemovePlayer(player) }
            )
        }
    }
}

@Composable
fun AddPlayerComponent(
    playerName: String,
    onNameChange: (String) -> Unit,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(
            top = dimensionResource(R.dimen.padding_medium),
            start = dimensionResource(R.dimen.padding_small),
            end = dimensionResource(R.dimen.padding_small)
        )
    ) {
        TextField(
            value = playerName,
            onValueChange = onNameChange,
            label = { Text(stringResource(R.string.player_name)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onAddClick() }
            )
        )
        TextButton(
            onClick = onAddClick
        ) {
            Text(stringResource(R.string.add))
        }
    }
}

@Composable
fun PlayerItem(
    playerName: String,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = dimensionResource(R.dimen.padding_medium)),
            text = playerName
        )
        IconButton(onClick = onRemove) {
            Icon(Icons.Filled.Close, contentDescription = "Remove player")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MTGAssassinTheme {
        MainScreen(
            playersList = testPlayersList,
            onRemovePlayer = {},
            onAddClick = {},
            onInputPlayerChange = {},
            inputName = ""
        )
    }
}