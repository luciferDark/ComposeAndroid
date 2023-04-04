package com.ll.compose

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ll.compose.ui.theme.ComposeAndroidTheme

val TAG: String = "Main"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                    shape = MaterialTheme.shapes.large
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column {
        Text(
            text = "Hello $name!",
            color = Color.Red,
            style = MaterialTheme.typography.caption,//设置style
            textDecoration = TextDecoration.Underline,//设置下划线或者中划线
            textAlign = TextAlign.Right,//文本对齐方式
        )
        Text(
            text = stringResource(id = R.string.app_name) + "你好！你好！你好！你好！你好！你好！你好！你好！你好！",
            color = Color.Blue,//设置颜色
            fontSize = 24.sp, //设置大小
            fontWeight = FontWeight.Bold,//设置粗体
            fontStyle = FontStyle.Italic,//设置斜体
            fontFamily = FontFamily(Font(R.font.bradhitc,FontWeight.Bold)),//设置字体
            letterSpacing = 5.sp, //设置文字间距
            textDecoration = TextDecoration.LineThrough,//设置下划线或者中划线
            textAlign = TextAlign.Center,//文本对齐方式
            maxLines = 1,//最大行数
            overflow = TextOverflow.Ellipsis,//文本切割方式
        )

        val text1 = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Red, fontSize = 22.sp)){
                append("你好：")
            }
            append(",我是")
            withStyle(style = SpanStyle(color = Color.Blue, fontSize = 24.sp)){
                append("北京")
            }
            append(",over！")
        }
        Text(
            text = text1
        )
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Red, fontSize = 22.sp)){
                    append("你好\n")
                }
                append("我是")
                withStyle(style = SpanStyle(color = Color.Blue, fontSize = 24.sp)){
                    append("北京\n")
                }
                append("over！\n")
            }
        )
        ClickableText(
            text = text1,
            onClick = {
                Log.d(TAG, "点击了文本，位置$it")
            })
        SelectionContainer(modifier = Modifier.fillMaxSize()) {
            Column {
                Text(text = "我是可选择的文本句子", fontSize = 20.sp)
                Text(text = "我是可选择的文本句子", fontSize = 20.sp)
                DisableSelection {
                    Text(text = "我是可选择的文本句子", fontSize = 20.sp)
                    Text(text = "我是可选择的文本句子", fontSize = 20.sp)
                }
                Text(text = "我是可选择的文本句子", fontSize = 20.sp)
                Text(text = "我是可选择的文本句子", fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 768, heightDp = 1080)
@Composable
fun DefaultPreview() {
    ComposeAndroidTheme {
        Greeting("Android")
    }
}