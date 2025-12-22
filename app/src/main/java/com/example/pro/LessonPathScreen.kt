package com.example.pro

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LessonPathScreen(onLessonClick: (Int) -> Unit) {

    val items = listOf(
        LessonPos(1, 40.dp, 95.dp),
        LessonPos(2, 150.dp, 180.dp),
        LessonPos(3, 230.dp, 280.dp),
        LessonPos(4, 150.dp, 380.dp),
        LessonPos(5, 60.dp, 480.dp),
        LessonPos(6, 165.dp, 570.dp)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp)
    ) {

        Spacer(modifier = Modifier.height(70.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFDCE6FF), RoundedCornerShape(30.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "CodLey",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(900.dp)
        ) {

            Canvas(modifier = Modifier.fillMaxSize()) {

                val path = Path()
                val pxItems = items.map {
                    PxPos(it.number, it.x.toPx(), it.y.toPx())
                }

                fun PxPos.cx() = x + 40.dp.toPx()
                fun PxPos.cy() = y + 40.dp.toPx()

                val first = pxItems.first()
                path.moveTo(first.cx(), first.cy())

                for (i in 0 until pxItems.lastIndex) {
                    val a = pxItems[i]
                    val b = pxItems[i + 1]

                    val midX = (a.cx() + b.cx()) / 2
                    val midY = (a.cy() + b.cy()) / 2

                    path.quadraticBezierTo(a.cx(), a.cy(), midX, midY)
                }

                val last = pxItems.last()
                path.lineTo(last.cx(), last.cy())

                drawPath(
                    path = path,
                    color = Color.Black,
                    style = Stroke(width = 6.dp.toPx())
                )
            }

            items.forEach {
                LessonItem(
                    number = it.number,
                    top = it.y,
                    left = it.x,
                    onClick = { onLessonClick(it.number) }
                )
            }
        }
    }
}
