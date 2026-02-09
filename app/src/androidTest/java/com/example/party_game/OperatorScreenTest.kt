package com.example.party_game

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.party_game.ui.operator.OperatorScreen
import com.example.party_game.ui.theme.PartyFoldTheme
import org.junit.Rule
import org.junit.Test

class OperatorScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun operatorScreen_displaysTaskAndButton() {
        composeTestRule.setContent {
            PartyFoldTheme {
                OperatorScreen(onSwitch = {})
            }
        }

        composeTestRule
            .onNodeWithTag("operator_task")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("to_helpers_button")
            .assertIsDisplayed()
    }
}
