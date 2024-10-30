package com.example.tictactoe.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tictactoe.R
import com.example.tictactoe.ui.theme.Accent
import com.example.tictactoe.ui.theme.Background
import com.example.tictactoe.ui.theme.Orange1
import com.example.tictactoe.ui.theme.Orange2

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToGame: (String, String, Boolean) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                Background
            )
    ) {
        Header()
        Body(
            onCreateGame = { homeViewModel.onCreateGame(navigateToGame) },
            onJoinGame = { gameId -> homeViewModel.onJoinGame(gameId, navigateToGame) }
        )
    }
}

@Composable
fun Body(onCreateGame: () -> Unit, onJoinGame: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(24.dp),
        colors = CardDefaults.cardColors(containerColor = Background),
        border = BorderStroke(2.dp, Orange1),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            var createGame by remember { mutableStateOf(true) }
            Switch(
                checked = createGame,
                onCheckedChange = { createGame = it },
                colors = SwitchDefaults.colors(checkedThumbColor = Orange2)
            )
            Spacer(modifier = Modifier.height(24.dp))
            AnimatedContent(targetState = createGame, label = "") {
                when (it) {
                    true -> {
                        CreateGame(onCreateGame)
                    }

                    false -> {
                        JoinGame(onJoinGame)
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun Header() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(36.dp))
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(24.dp)
                .clip(RoundedCornerShape(24.dp))
                .border(
                    2.dp,
                    Orange1,
                    RoundedCornerShape(24.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.logo_app
                ),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            )
        }
        Text(
            "TIC TAC TOE",
            fontSize = 28.sp,
            color = Orange1,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CreateGame(onCreateGame: () -> Unit) {
    Button(
        onClick = { onCreateGame() },
        colors = ButtonDefaults.buttonColors(containerColor = Orange1)
    ) {
        Text("Create Game", color = Accent)
    }
}

@Composable
fun JoinGame(onJoinGame: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Orange1,
                focusedTextColor = Accent,
                unfocusedTextColor = Accent,
                unfocusedBorderColor = Accent,
                cursorColor = Orange1
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onJoinGame(text) },
            enabled = text.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(containerColor = Orange1)
        ) {
            Text(text = "Join to game", color = Accent)
        }
    }
}