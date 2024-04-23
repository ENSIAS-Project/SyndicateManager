package com.ensias.syndicatemanager.ui.view.components

//header and footer
//scaffold

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ensias.syndicatemanager.R
import com.ensias.syndicatemanager.ui.theme.SyndicateManagerTheme


//header and footer

@Composable
fun BgForAllScreens() {


    Box(
        Modifier
            .fillMaxWidth()
            .height(130.dp)

    ) {

        DemiCercle(color = MaterialTheme.colorScheme.secondaryContainer, diameter = 80.dp)
        //Text(text = "${s}")

        Row(
            modifier = Modifier
                .padding(top = 80.dp, start = 10.dp, end = 50.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .height(10.dp)
                    .padding(start = 300.dp),

                ) {
                Circle(color = MaterialTheme.colorScheme.onSecondary, radius = 35.dp)
            }


        }
        Column(
            modifier = Modifier
                .height(30.dp)
                .padding(start = 350.dp)


        ) {
            Circle(color = MaterialTheme.colorScheme.onSecondary, radius = 70.dp, 0.5f)

        }

        ImageLogo()


    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
    }

}

@Composable
fun ImageLogo() {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .size(50.dp)
            .clip(MaterialTheme.shapes.small)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )
    }

}


@Composable
fun DemiCercle(color: Color, diameter: Dp) {

    Canvas(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        val shadowColor = Color.Gray.copy(alpha = 0.4f)

        scale(scaleX = 15f, scaleY = 7f) {

            drawCircle(color = color, radius = 15.dp.toPx())

        }
    }

    //second method

    /*//first method
   //rectangle
       val canvasQuadrantSize=size
       drawRect(
          color=color,
           size=canvasQuadrantSize
       )
       //arc
      val radius =size.width/2
       drawArc(
           color=color,
           startAngle=0f,
           sweepAngle=200f,
           useCenter=true,
           topLeft=Offset(0f,100f),
           size= Size(radius*2,diameter.toPx()),
           style=Fill
       )*/


}


@Composable
fun Circle(
    color: Color,
    radius: Dp,
    opacity: Float = 1f
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val circleRadius = radius.toPx()
        val center = Offset(size.width / 2, size.height / 2)
        drawCircle(
            color = color.copy(alpha = opacity),
            radius = circleRadius,
            center = center,
        )
    }
}

@Composable
fun footer(color: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),

        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
        // Aligner les éléments en bas
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp) // Hauteur du rectangle
                .background(color = color)
        )
    }
}

@PreviewLightDark
@Composable
fun PreviewBG() {
    SyndicateManagerTheme {

        BgForAllScreens()


    }
}
