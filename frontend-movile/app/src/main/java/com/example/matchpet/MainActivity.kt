package com.example.matchpet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matchpet.ui.theme.MatchPetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatchPetTheme {
                MatchPetApp()
            }
        }
    }
}

@Composable
fun MatchPetApp() {
    var currentScreen by remember { mutableStateOf("home") }

    when (currentScreen) {
        "home" -> HomeScreen(
            onAdoptarClick = {
                currentScreen = "register"
            }
        )
        "register" -> RegisterScreen(
            onBackClick = {
                currentScreen = "home"
            },
            onLoginClick = {
                currentScreen = "login"
            },
            onSuccessClick = {
                currentScreen = "success"
            }
        )
        "login" -> LoginScreen(
            onRegisterClick = {
                currentScreen = "register"
            },
            onBackClick = {
                currentScreen = "home"
            }
        )
        "success" -> SuccessScreen(
            onContinueClick = {
                currentScreen = "home"
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onAdoptarClick: () -> Unit = {}) {
    val lightBlue = Color(0xFFB8E6F5)
    val darkBlue = Color(0xFF2C3E50)
    val coral = Color(0xFFFF9B87)
    val lightPink = Color(0xFFFFF5F5)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Logo",
                            tint = Color(0xFF4A9DB5),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "MatchPet",
                            fontWeight = FontWeight.Bold,
                            color = darkBlue
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = lightBlue
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(lightBlue)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Icono principal
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Pet Icon",
                tint = darkBlue,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Título principal
            Text(
                text = "Encuentra a tu compañero ideal",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = darkBlue,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botones de acción
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onAdoptarClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = darkBlue
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Adoptar", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = coral
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ayudar", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = darkBlue
                    )
                ) {
                    Text("Soy Refugio", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Tarjeta de bienvenida
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bienvenido a MatchPet",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = darkBlue
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Nuestra plataforma te ayuda a encontrar el compañero perfecto. Explora perfiles de mascotas en busca de hogar, conecta con refugios y dale una segunda oportunidad a un mejor amigo.",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Features
                    FeatureCard(
                        icon = Icons.Default.Search,
                        title = "Búsqueda Inteligente",
                        description = "Encuentra a tu mascota ideal según tus preferencias y estilo de vida",
                        backgroundColor = lightPink
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    FeatureCard(
                        icon = Icons.Default.Verified,
                        title = "Refugios Verificados",
                        description = "Todos nuestros refugios están verificados para tu seguridad y tranquilidad",
                        backgroundColor = lightPink
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    FeatureCard(
                        icon = Icons.Default.Favorite,
                        title = "Adopción Responsable",
                        description = "Te guiamos en cada paso del proceso para una adopción exitosa",
                        backgroundColor = lightPink
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun FeatureCard(
    icon: ImageVector,
    title: String,
    description: String,
    backgroundColor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color(0xFF4A9DB5),
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2C3E50),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MatchPetTheme {
        HomeScreen(onAdoptarClick = {})
    }
}