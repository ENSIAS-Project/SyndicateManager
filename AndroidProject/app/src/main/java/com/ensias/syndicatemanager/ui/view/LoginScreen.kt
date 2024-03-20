package com.ensias.syndicatemanager.ui.view

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.service.impl.MockAccountService
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme
import com.ensias.syndicatemanager.ui.view.components.EmailField
import com.ensias.syndicatemanager.ui.view.components.PasswordField
import com.ensias.syndicatemanager.viewmodels.LoginViewModel
@Composable

fun LoginScreen(
    lvm: LoginViewModel = hiltViewModel()
){

    // to ensure recomposition everytime uiState changes
    val uiState by lvm.uiState
    Column(
        modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.spacedBy(10.dp,alignment=Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Spacer(modifier = Modifier.padding(150.dp))
        EmailField(value = uiState.email, lvm::setname)
        Spacer(modifier=Modifier.padding(10.dp))//space
        PasswordField(value = uiState.password,lvm::setpass)
        Spacer(modifier=Modifier.padding(20.dp))//space

        if (uiState.logged){
            Button(
                onClick = { lvm.Logout() },
                modifier = Modifier.width(150.dp),
                colors=ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF))
            )   {
                Text(text = "logout",Modifier.padding(vertical=8.dp),Color.Black)
            }
        }else{
            Button(
                onClick = { lvm.Login() },modifier = Modifier.width(150.dp),
                colors=ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF))
            )   {
                Text(text = "login",Modifier.padding(vertical=8.dp),Color.Black
                )
            }
        }
            Spacer(modifier=Modifier.padding(20.dp))
        }



}


@Composable
fun Modifier.roundedCornerShape():Modifier=this then
        Modifier.clip(MaterialTheme.shapes.medium)

@Composable
fun Background(){
    Box(modifier= Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background))
    {
        //trace1
        Image(painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            modifier= Modifier,
            contentScale = ContentScale.FillBounds

        )
        //trace2
        Box(modifier = Modifier
            .background(
                Color.Black.copy(alpha = 0.2f),
                RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp)
            )
            .align(Alignment.BottomCenter)
            .height(360.dp)
            .fillMaxWidth()
        )
        //image logo

        Box(
            modifier= Modifier
                .padding(16.dp)
                .size(200.dp)
                .clip(MaterialTheme.shapes.small)
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                contentScale = ContentScale.Fit,

                        modifier= Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.small)
                    .background(color = Color.White)

            )

        }
        //text :Welcome neighbor
    /*  Text(
            text="WELCOME NEIGHBOR",
            style= TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize=25.sp,
                color=Color.Black,
                //textAlign = TextAlign.Center
            ), modifier = Modifier
                .offset(y=300.dp,x=70.dp)
        )*/
        Box(
            modifier = Modifier
                .fillMaxSize(),
               contentAlignment = Alignment.Center

        ) {
            Text(
                text = "WELCOME NEIGHBOR",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Color.Black,
                ) ,  modifier = Modifier.offset(y = (-40).dp)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview(){
    Background()
    LoginScreen(LoginViewModel(MockAccountService()))

}