package com.example.party_game.ui.operator

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.party_game.ui.theme.PartyFoldTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.party_game.ui.common.ScreenScaffold
import com.example.party_game.ui.common.TestTags

@Composable
fun OperatorScreen(
    onSwitch: () -> Unit,
    modifier: Modifier = Modifier
) {
    ScreenScaffold(title = "Operator") {
        Text(
            text = "Defuse the device!",
            modifier = Modifier.testTag(TestTags.OPERATOR_TASK)
        )

        Button(
            onClick = onSwitch,
            modifier = Modifier.testTag(TestTags.TO_HELPERS_BUTTON)
        ) {
            Text("Show Helpers")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OperatorPreview() {
    PartyFoldTheme {
        OperatorScreen(onSwitch = {})
    }
}
