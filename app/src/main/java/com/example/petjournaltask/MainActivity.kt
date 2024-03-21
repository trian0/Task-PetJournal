package com.example.petjournaltask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomePage()
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun HomePage() {

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val fontName = GoogleFont("Playfair Display")

    val fontFamily = FontFamily(
        Font(
            googleFont = fontName,
            fontProvider = provider,
            weight = FontWeight.Bold,
            style = FontStyle.Normal,
        )
    )

    var expandedPort by remember { mutableStateOf(false) }
    var selectedItemPort by remember { mutableStateOf("Porte do seu pet") }

    var expandedBreed by remember { mutableStateOf(false) }
    var selectedItemBreed by remember { mutableStateOf("Raça do seu pet") }

    val stroke = Stroke(
        width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(70f, 10f), 0f)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
            .padding(horizontal = 20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.rastro),
                    alignment = Alignment.Center
                )
        ) {
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.logo_purple),
                contentDescription = "logo",
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Nos conte mais!\nQual a raça de Bolinha?",
                fontFamily = fontFamily,
                style = TextStyle(
                    fontSize = 25.sp,
                ),
                color = colorResource(id = R.color.purple_500)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Porte",
                fontFamily = fontFamily,
                style = TextStyle(
                    fontSize = 10.sp,
                ),
                modifier = Modifier.padding(start = 15.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            ExposedDropdownMenuBox(
                expanded = expandedPort,
                onExpandedChange = { expandedPort = it },
            ) {
                TextField(
                    value = selectedItemPort,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPort)
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .menuAnchor()
                        .fillMaxWidth()
                        .drawBehind {
                            drawRoundRect(
                                color = Color.Gray,
                                style = stroke,
                                cornerRadius = CornerRadius(8.dp.toPx())
                            )
                        },
                    shape = RoundedCornerShape(8.dp)
                )

                ExposedDropdownMenu(
                    expanded = expandedPort,
                    onDismissRequest = { expandedPort = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Pequeno")
                        },
                        onClick = {
                            selectedItemPort = "Pequeno"
                            expandedPort = false
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(text = "Médio")
                        },
                        onClick = {
                            selectedItemPort = "Médio"
                            expandedPort = false
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(text = "Grande")
                        },
                        onClick = {
                            selectedItemPort = "Grande"
                            expandedPort = false
                        }
                    )
                }
            }

            Text(
                text = "* Campo obrigatório",
                fontFamily = fontFamily,
                style = TextStyle(
                    fontSize = 10.sp,

                ),
                color = Color.Gray,
                modifier = Modifier.padding(start = 15.dp, top = 10.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Raça",
                fontFamily = fontFamily,
                style = TextStyle(
                    fontSize = 10.sp,
                ),
                modifier = Modifier.padding(start = 15.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            ExposedDropdownMenuBox(
                expanded = expandedBreed,
                onExpandedChange = { expandedBreed = it },
            ) {
                TextField(
                    value = selectedItemBreed,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedBreed)
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .menuAnchor()
                        .fillMaxWidth()
                        .drawBehind {
                            drawRoundRect(
                                color = Color.Gray,
                                style = stroke,
                                cornerRadius = CornerRadius(8.dp.toPx())
                            )
                        },
                    shape = RoundedCornerShape(8.dp)
                )

                ExposedDropdownMenu(
                    expanded = expandedBreed,
                    onDismissRequest = { expandedBreed = false }
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Persa")
                        },
                        onClick = {
                            selectedItemBreed = "Persa"
                            expandedBreed = false
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(text = "Slamês")
                        },
                        onClick = {
                            selectedItemBreed = "Slamês"
                            expandedBreed = false
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(text = "Ashera")
                        },
                        onClick = {
                            selectedItemBreed = "Ashera"
                            expandedBreed = false
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(text = "Pug")
                        },
                        onClick = {
                            selectedItemBreed = "Pug"
                            expandedBreed = false
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(text = "Bulldog")
                        },
                        onClick = {
                            selectedItemBreed = "Bulldog"
                            expandedBreed = false
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(text = "Shih Tzu")
                        },
                        onClick = {
                            selectedItemBreed = "Shih Tzu"
                            expandedBreed = false
                        }
                    )

                    DropdownMenuItem(
                        text = {
                            Text(text = "Sem Raça")
                        },
                        onClick = {
                            selectedItemBreed = "Sem Raça"
                            expandedBreed = false
                        }
                    )
                }
            }

            Text(
                text = "* Campo obrigatório",
                fontFamily = fontFamily,
                style = TextStyle(
                    fontSize = 10.sp,

                    ),
                color = Color.Gray,
                modifier = Modifier.padding(start = 15.dp, top = 10.dp)
            )

            Image(
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.pet_heart),
                contentDescription = "logo",
            )

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .width(150.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, colorResource(id = R.color.purple_500))
                ) {
                    Text(
                        text = "Voltar",
                        color = colorResource(id = R.color.purple_500),
                        modifier = Modifier.padding(5.dp)
                    )
                }

                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(150.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.purple_500)
                    )
                ) {
                    Text(
                        text = "Continuar",
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomePage()
}