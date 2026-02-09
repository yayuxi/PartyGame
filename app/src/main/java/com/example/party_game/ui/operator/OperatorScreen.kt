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
    taskText: String,
    onNext: () -> Unit,
    onSwitch: () -> Unit,
    modifier: Modifier = Modifier
) {
    ScreenScaffold(title = "Operator") {
        Text(
            text = taskText,
            modifier = Modifier.testTag("operator_task")
        )

        Button(onClick = onNext) {
            Text("Next Step")
        }

        Button(
            onClick = onSwitch,
            modifier = Modifier.testTag("to_helpers_button")
        ) {
            Text("Show Helpers")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun OperatorPreview() {
    PartyFoldTheme {
        OperatorScreen(
            taskText = "Preview task",
            onNext = {},
            onSwitch = {}
        )
    }
}

