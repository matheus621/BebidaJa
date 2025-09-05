package com.example.bebidaja.ui.auth.login

import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.bebidaja.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SigninScreen(
    onSuccess: () -> Unit,
    onBack: () -> Unit,
    onCreateAccount: () -> Unit,
    onGoogle: () -> Unit = {},
    onFacebook: () -> Unit = {},
) {
    var user by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    var showPass by rememberSaveable { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    val enabled = user.isNotBlank() && pass.isNotBlank()

    fun attemptLogin() {
        val ok = user.trim().equals("matheus", ignoreCase = true) && pass == "123456"
        if (ok) {
            error = null; onSuccess()
        } else {
            error = "Usuário ou senha inválidos."
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Entrar") },
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(inner)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))

            // LOGO (igual Welcome)
            Image(
                painter = painterResource(R.drawable.bebidajalogo),
                contentDescription = "Logo BebidaJá",
                modifier = Modifier
                    .fillMaxWidth(0.42f)
                    .sizeIn(220.dp)
            )

            Spacer(Modifier.height(32.dp))

            // Usuário
            OutlinedTextField(
                value = user,
                onValueChange = { user = it },
                label = { Text("Usuário") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(Modifier.height(12.dp))

            // Senha
            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text("Senha") },
                singleLine = true,
                visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { showPass = !showPass }) {
                        Icon(
                            imageVector = if (showPass) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if (showPass) "Ocultar senha" else "Mostrar senha"
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            if (error != null) {
                Spacer(Modifier.height(8.dp))
                Text(
                    error!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(Modifier.height(24.dp))

            // Entrar (cheio, cantos arredondados — estilo Welcome)
            Button(
                onClick =  { attemptLogin() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(26.dp), // cantos bem arredondados
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) { Text("Entrar", style = MaterialTheme.typography.titleSmall) }

            Spacer(Modifier.height(12.dp))

            // Voltar (texto, mesmo alinhamento)
            TextButton(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(26.dp),
                border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground
                )
            ) { Text("Voltar") }

            Spacer(Modifier.height(24.dp))

            // Social login (quadrados lado a lado)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SocialSquare(
                    onClick = onFacebook,
                    label = "Facebook",
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_facebook),
                        contentDescription = "Entrar com Facebook",
                        modifier = Modifier.size(22.dp)
                    )
                }

                SocialSquare(
                    onClick = onGoogle,
                    label = "Google",
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_google),
                        contentDescription = "Entrar com Google",
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // "Você não tem uma conta? Criar Conta"
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Você não tem uma conta?  ",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground)
                Text(
                    text = "Criar Conta",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { onCreateAccount() }
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun SocialSquare(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Surface(
            onClick = onClick, // melhor que .clickable no child
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                content()
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(
            label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        )
    }
}

