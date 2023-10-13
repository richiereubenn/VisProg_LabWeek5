package com.example.visprog_labweek5.ui.theme.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.visprog_labweek5.R
import com.example.visprog_labweek5.viewmodel.Soal2ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Soal2(soal2: Soal2ViewModel = viewModel()) {
    var sks by rememberSaveable { mutableStateOf("") }
    var score by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }

    val (refreshFlag, setRefreshFlag) = remember { mutableStateOf(false) }
    val courseList by soal2.uiState.collectAsState(emptyList())


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF4674F5)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Courses",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .padding(horizontal = 15.dp)
                        )
                        Text(
                            text = "Total SKS : ${soal2.totalSKS()}",
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .padding(horizontal = 15.dp),
                            fontSize = 15.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = "IPK : ${String.format("%.2f", soal2.IPK())}",
                            modifier = Modifier
                                .padding(top = 5.dp, bottom = 15.dp)
                                .padding(horizontal = 15.dp),
                            fontSize = 15.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.baseline_account_circle_24),
                        contentDescription = "profile",
                        modifier = Modifier
                            .padding(horizontal = 25.dp)
                            .size(80.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .padding(horizontal = 15.dp)
                ) {
                    OutlinedTextField(
                        value = sks,
                        onValueChange = {
                            sks = it
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        label = { Text("SKS", color = Color.DarkGray,) },
                        modifier = Modifier
                            .weight(1f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF4674F5),
                            textColor = Color.DarkGray,
                        )
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    OutlinedTextField(
                        value = score,
                        onValueChange = {
                            score = it
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        label = { Text("Score", color = Color.DarkGray)},
                        modifier = Modifier
                            .weight(1f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF4674F5),
                            textColor = Color.DarkGray,
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        label = { Text("Name", color = Color.DarkGray)},
                        modifier = Modifier
                            .weight(1.5f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF4674F5),
                            textColor = Color.DarkGray,
                        )
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Button(
                        onClick = {
                            soal2.addCourse(name, score.toDouble(), sks.toInt())
                            sks = ""
                            score = ""
                            name = ""
                        },
                        modifier = Modifier.padding(top = 4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4674F5)
                        ),
                        enabled = sks.isNotBlank() && score.isNotBlank() && name.isNotBlank()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_add_24),
                            contentDescription = "Add",
                            modifier = Modifier.padding(7.dp)
                        )
                    }
                }
            }
            items(courseList) { subject ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp)
                        .padding(horizontal = 15.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = Color(0xFFD1DDFF)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Name : ${subject.name}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.DarkGray
                            )
                            Text(
                                text = "SKS : ${subject.sks}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray
                            )
                            Text(
                                text = "Score : ${subject.score}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.DarkGray
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.baseline_delete_forever_24),
                            contentDescription = "Add",
                            modifier = Modifier.clickable{
                                soal2.removeCourse(subject)
                                setRefreshFlag(!refreshFlag)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal2Preview() {
    Soal2()
}