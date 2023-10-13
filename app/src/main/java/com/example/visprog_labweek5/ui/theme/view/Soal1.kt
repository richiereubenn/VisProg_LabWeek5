package com.example.visprog_labweek5.ui.theme.view

import Soal1ViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Soal1(soal1: Soal1ViewModel = viewModel()) {
    val gameModel by soal1.uiState.collectAsState()
    var guess by rememberSaveable { mutableStateOf("") }
    var gameOver by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Guess the Number",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Card(
            modifier = Modifier.padding(horizontal = 30.dp).padding(top = 10.dp),
            colors = CardDefaults.elevatedCardColors(
                    containerColor = Color(0xFFD2DBF1)
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Number of Guesses ${gameModel.chance}",
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .background(Color(0XFF0031B8), shape = RoundedCornerShape(10.dp))
                        .padding(8.dp)
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(45.dp))
                    Text(
                        text = gameModel.random.toString(),
                        modifier = Modifier,
                        fontSize = 50.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "From 1 to 10 Guess the Number",
                        modifier = Modifier.padding(top = 5.dp),
                        fontSize = 15.sp
                    )
                    Text(
                        text = "Score : ${gameModel.score}",
                        modifier = Modifier.padding(top = 5.dp),
                        fontSize = 15.sp
                    )
                    OutlinedTextField(
                        value = guess,
                        onValueChange = {
                            guess = it
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        label = { Text("Enter your guess")},
                        modifier = Modifier.padding(top = 16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0XFF0031B8),
                            textColor = Color.DarkGray,
                        )
                    )
                }
            }
        }
        Button(
            onClick = {
                soal1.answer(guess.toInt())
                soal1.play()
                guess = ""
            },
            enabled = guess.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0031B8)
            ),
        ) {
            Text(
                text = "Submit",
            )
        }
        if(gameModel.gameOver){
            AlertDialog(
                onDismissRequest = {
                    gameOver = false
                },
                containerColor = Color(0xFFD2DBF1),
                title = { Text("GameOver!") },
                text = {
                    Text("Your Score : ${gameModel.score}")
                },
                confirmButton = {
                    Row {
                        Button(
                            onClick = {
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0031B8)
                            )
                        ) {
                            Text(text = "Exit")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                soal1.gameReset()
                                gameOver = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF0031B8)
                            )
                        ) {
                            Text(text = "Play Again")
                        }
                    }
                }
            )
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal1Preview() {
    Soal1()
}