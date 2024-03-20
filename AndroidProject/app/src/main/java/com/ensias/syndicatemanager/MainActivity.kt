    package com.ensias.syndicatemanager
/*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.LoginScreen
import dagger.hilt.android.AndroidEntryPoint

    @AndroidEntryPoint

    class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           SyndicateManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                   // LoginView()
                    LoginScreen()
                }
            }
        }
    }
}


@Composable
fun LoginView(){
    SyndicateManagerTheme {
        background()
        Column(
            Modifier
                .padding(2.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextInput()
            Spacer(modifier = Modifier.padding(10.dp))

            TextInput()
            Spacer(modifier = Modifier.padding(20.dp))
            SyndicateManagerTheme {
                Button(onClick={},
                    modifier = Modifier.width(150.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6650a4))


                ){
                    Text("LOGIN", Modifier.padding(vertical = 8.dp))
                }
            }

            Spacer(modifier = Modifier.padding(40.dp))

        }
    }

}
@Composable
fun TextInput(){
    var value:String by remember { mutableStateOf("") }
    
    TextField(value = value, onValueChange = {value = it},
        modifier = Modifier
            .width(260.dp)
            .roundedCornerShape()
            )
}
    @Composable
    fun Modifier.roundedCornerShape(): Modifier = this then Modifier.clip(MaterialTheme.shapes.medium)
@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    SyndicateManagerTheme {
        LoginView()
    }
}@Composable
    fun background(){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

           Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            )
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.2f))
                    .align(Alignment.BottomCenter)
                    .height(450.dp)
                    .fillMaxWidth()



            )

            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(200.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.primary)
            ){
                // Image du logo
                Image(
                    painter = painterResource(id = R.drawable.logo), // Remplacez par le chemin de votre image
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        //   .clip(MaterialTheme.shapes.small)
                        .background(color = Color.White)
                )
            }
            // Texte de bienvenue

               /* style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.Black.copy(alpha = 0.6f))
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp)*/
                Text(
                    text = "WELCOME NEIGHBOR",
                    style = TextStyle(
                     //   fontSize = MaterialTheme.typography.h1.fontSize,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Color.Black

                    ), modifier = Modifier
                        .offset(y = 300.dp, x = 70.dp)
                )
                   }


    }*/