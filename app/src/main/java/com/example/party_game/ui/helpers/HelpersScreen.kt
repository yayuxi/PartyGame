package com.example.party_game.ui.helpers

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.party_game.ui.theme.PartyFoldTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.party_game.ui.common.ScreenScaffold

@Composable
fun HelpersScreen(
    onSwitch: () -> Unit,
    modifier: Modifier = Modifier
) {
    ScreenScaffold(title = "Helpers") {
        Text(
            text = "Give instructions clearly!",
            modifier = Modifier.testTag("helpers_instructions")
        )

        Button(
            onClick = onSwitch,
            modifier = Modifier.testTag("to_operator_button")
        ) {
            Text("Back to Operator")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HelpersPreview() {
    PartyFoldTheme {
        HelpersScreen(onSwitch = {})
    }
}
