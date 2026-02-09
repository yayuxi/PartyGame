package com.example.party_game

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.party_game.ui.common.FoldRequiredScreen
import com.example.party_game.ui.theme.PartyFoldTheme
import org.junit.Rule
import org.junit.Test

class FoldRequiredScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun foldedState_showsUnfoldMessage() {
        composeTestRule.setContent {
            PartyFoldTheme {
                FoldRequiredScreen()
            }
        }

        composeTestRule
            .onNodeWithText("Please unfold your device to play 🎉")
            .assertIsDisplayed()
    }
}
