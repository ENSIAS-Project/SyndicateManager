package com.ensias.syndicatemanager.ui.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme


@Composable
fun LoginBackground(h:Int,Y:Int){
    Box(modifier= Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        )
    {
        //trace1
        Image(painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            modifier= Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primaryContainer),
            contentScale = ContentScale.FillBounds


        )
        //trace2
        Box(modifier = Modifier
            .background(
                Color.Black.copy(alpha = 0.2f),
                RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp)
            )
            .align(Alignment.BottomCenter)
          //  .height(360.dp)
            .height(h.dp)
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
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                contentDescription = null,
                //contentScale = ContentScale.FillBounds,

                modifier= Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.small)
                    .background(color = Color.Transparent)
            )
        }
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
                    color = MaterialTheme.colorScheme.onBackground,
                ) , // modifier = Modifier.offset(y = (-40).dp)
                modifier = Modifier.offset(y =Y.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun preview(){
    SyndicateManagerTheme {
        LoginBackground(360,(-40))

    }
}
