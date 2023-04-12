package com.ll.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ButtonLearn() {
    var clickAble by remember { mutableStateOf(false) }
    var btn2Text by remember { mutableStateOf(if (clickAble) "现在可以点击试试了" else "你现在无法点击") }
    Column() {
        Button(onClick = {
            println("你点击了下我")
            clickAble = !clickAble
            btn2Text = if (clickAble) "现在可以点击试试了" else "你现在无法点击"
        }) {
            Text(text = "点下试试")
        }
        Button(
            enabled = clickAble, onClick = { println("你点了下") },
            colors = object : ButtonColors {
                @Composable
                override fun backgroundColor(enabled: Boolean): State<Color> {
                    return rememberUpdatedState(if (clickAble) Color.Yellow else Color.Gray)
                }
                @Composable
                override fun contentColor(enabled: Boolean): State<Color> {
                    return rememberUpdatedState(if (clickAble) Color.Red else Color.Black)
                }
            },
            contentPadding = PaddingValues(20.dp)
        ) {
            Text(text = btn2Text)
            Button(
                onClick = { println("你点了按钮上的按钮") },
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(4.dp, Color.Blue),
                contentPadding = PaddingValues(
                    start = 10.dp,
                    end = 10.dp,
                    top = 8.dp,
                    bottom = 4.dp
                )
            ) {
                Text(text = "按钮中的按钮")
            }
        }
    }
}

@Composable
@Preview
fun ButtonLearnPre() {
    ButtonLearn()
}