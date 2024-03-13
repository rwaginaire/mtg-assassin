package com.example.mtgassassin.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mtgassassin.model.Player
import com.example.mtgassassin.ui.theme.MTGAssassinTheme
import com.example.mtgassassin.utils.TestData

@Composable
fun ResultsScreen(
    players: List<Player>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        for (player in players) {
            Text(text = "${player.name} doit éliminer ${player.target}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
    MTGAssassinTheme {
        ResultsScreen(TestData.playersList)
    }
}