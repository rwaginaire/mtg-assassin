package com.example.mtgassassin.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mtgassassin.R
import com.example.mtgassassin.model.Player
import com.example.mtgassassin.utils.TestData

@Composable
fun DiscoverTargetScreen(
    player: Player,
    onShowClick: () -> Unit,
    onConfirm: () -> Unit,
    isTargetHidden: Boolean = true,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.take_phone, player.name),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = onShowClick,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Text(stringResource(R.string.show_target))
        }

        if (!isTargetHidden) {
            TargetDialog(
                targetName = player.target,
                onConfirm = onConfirm
            )
        }
    }
}

@Composable
private fun TargetDialog(
    targetName: String?,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = stringResource(R.string.target_shown)) },
        text = {
            Text(
                text = targetName ?: "",
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )
       },
        modifier = modifier,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = stringResource(R.string.ok))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DiscoverTargetScreenPreview() {
    DiscoverTargetScreen(
        player = TestData.playersList[0],
        onShowClick = {},
        onConfirm = {}
    )
}

@Preview(showBackground = true)
@Composable
fun TargetDialogPreview() {
    TargetDialog(
        targetName = TestData.playersList[0].target,
        onConfirm = {}
    )
}