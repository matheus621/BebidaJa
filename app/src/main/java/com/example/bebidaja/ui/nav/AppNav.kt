import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bebidaja.R
import com.example.bebidaja.ui.auth.login.SigninScreen
import com.example.bebidaja.ui.splash.SplashScreen
import com.example.bebidaja.ui.welcome.WelcomeAuthScreen

@Composable
fun AppNav() {
    val nav = rememberNavController()
    NavHost(nav, startDestination = "splash") {
        composable("splash") {
            SplashScreen(
                onFinished = { nav.navigate("home") { popUpTo("splash") { inclusive = true } } })
        }
        composable("home") {
            WelcomeAuthScreen(
                onCreateAccount = { nav.navigate("signup") },
                onSignIn = { nav.navigate("signin") })
        }
        composable("signin") {
            SigninScreen(
                onSuccess = {
                    nav.navigate("catalog") { popUpTo("home") { inclusive = true } }
                },
                onBack = { nav.popBackStack() },
                onCreateAccount = { nav.navigate("signup") },
                onGoogle = { /* TODO: Google One Tap / Firebase */ },
                onFacebook = { /* TODO: Facebook Login */ }
            )
        }
        composable("signup") { /* TODO: tela de cadastro */ }
        composable("catalog") { /* TODO: tela principal após login */ }
    }
}

@Composable
fun AppRoot() {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.bebidajalogo), // sua logo em drawable/
                contentDescription = "BebidaJá", modifier = Modifier.size(120.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text("Em construção…", style = MaterialTheme.typography.titleMedium)
        }
    }
}
