package com.ll.compose

import android.os.Debug
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ContentInfoCompat.Flags
import kotlin.random.Random


@Composable
fun CanvasLearn() {
    // 生成随机种子
    var seed by remember {
        mutableStateOf(System.currentTimeMillis())
    }
    var triangleCount by remember {
        mutableStateOf(3)
    }
    var triangleCountPre by remember {
        mutableStateOf(3)
    }
    // 使用随机种子创建 Random 对象
    val random = Random(seed)
    Column {
        Button(
            modifier = Modifier,
            onClick = {
                seed = System.currentTimeMillis()
            }) {
            Text(text = "重新画")
        }
        OutlinedTextField(
            value = triangleCount.toString(),
            onValueChange = {
                if(it.isEmpty())
                    return@OutlinedTextField
                try {
                    var count = it.toInt()

                    triangleCount = if(count > 1000) 1000 else if(count < 0) 0 else count
                    if (triangleCountPre == triangleCount) return@OutlinedTextField
                    triangleCountPre = triangleCount
                } catch (e:Exception){
                    Log.d(TAG, "CanvasLearn: ${e.message}")
                    triangleCount = 3
                    if (triangleCountPre == triangleCount) return@OutlinedTextField
                    triangleCountPre = triangleCount
                    seed = System.currentTimeMillis()
                }
            },
            label = {
                Text(text = "输入3角形数量：", fontSize = 8.sp)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions (
                onNext = {
                    triangleCount = triangleCount
                    if (triangleCountPre != triangleCount) {
                        triangleCountPre = triangleCount
                        seed = System.currentTimeMillis()
                    }
                }
            ),
        )
        Canvas(
            modifier = Modifier
                .size(500.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(10.dp))
                .padding(10.dp)
        ) {
            repeat(triangleCount) {
                drawRoundTriangle(
                    rect = Rect(Offset.Zero, size),
                    point1 = Pair(
                        random.nextInt(0, size.width.toInt()).toFloat(),
                        random.nextInt(0, size.height.toInt()).toFloat()
                    ),
                    point2 = Pair(
                        random.nextInt(0, size.width.toInt()).toFloat(),
                        random.nextInt(0, size.height.toInt()).toFloat()
                    ),
                    point3 = Pair(
                        random.nextInt(0, size.width.toInt()).toFloat(),
                        random.nextInt(0, size.height.toInt()).toFloat()
                    ),
                    colorLine = generateRandomColor(random)
                )
            }
        }
    }
}

fun generateRandomColor(random: Random = Random): Color {
    val red = random.nextInt(256)
    val green = random.nextInt(256)
    val blue = random.nextInt(256)
    return Color(red, green, blue)
}

fun DrawScope.drawLine(
    startX: Float,
    startY: Float,
    endX: Float,
    endY: Float,
    color: Color = Color.Black,
    strokeWidth: Float = 1f,
) {
    drawLine(
        color = color,
        start = Offset(startX, startY),
        end = Offset(endX, endY),
        strokeWidth = strokeWidth,
    )
}

fun DrawScope.drawRoundTriangle(
    rect: Rect,
    colorLine: Color = Color.Black,
    point1: Pair<Float, Float> = Pair(rect.topCenter.x, rect.topCenter.y),
    point2: Pair<Float, Float> = Pair(rect.bottomRight.x, rect.bottomRight.y),
    point3: Pair<Float, Float> = Pair(rect.bottomLeft.x, rect.bottomLeft.y),
    paint: Paint? = null
) {
    val triangleShape = Path().apply {
        moveTo(point1.first, point1.second)
        lineTo(point2.first, point2.second)
        lineTo(point3.first, point3.second)
        close()
    }
    var _paint = paint
    if (null == _paint) {
        _paint = Paint().apply {
            color = colorLine
            pathEffect = PathEffect.cornerPathEffect(rect.maxDimension / 5)
        }
    }
    drawIntoCanvas {
        it.drawOutline(
            outline = Outline.Generic(triangleShape),
            paint = _paint
        )

    }
}
/*
internal class RounderTriangleShape(val cornerRadius: CornerRadius) : Shape{
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        TODO("Not yet implemented")
        val path = Path().apply {
            val x1 = size.width/2
            val y1 = 0f
            val x2 = 0f
            val y2 = size.height
            val x3 = size.width
            val y3 = size.height
            moveTo(x1,y1)
            lineTo(x2,y2)
            lineTo(x3,y3)
            close()

            addRoundRect(RoundRect(0f,0f,size.width, size.height, cornerRadius = cornerRadius))
        }

        Outline.Generic(path = path)
    }
}*/

@Preview
@Composable
fun CanvasLearnPre() {
    CanvasLearn()
}