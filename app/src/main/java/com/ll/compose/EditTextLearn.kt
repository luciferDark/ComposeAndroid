import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EditTextLearn() {
    var textStr by remember { mutableStateOf("你好!") }
    var _context = LocalContext.current
    Column {
        TextField(
            value = textStr,
            onValueChange = {
                textStr = it
            },
            label = {
                Text(text = "输入提示：")
            },
            textStyle = TextStyle(color = Color.Red, fontWeight = FontWeight.Bold),//修改文本格式
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,//字母大写
                keyboardType = KeyboardType.Text,//文本格式
                autoCorrect = true,//自动修正
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    Toast.makeText(_context,
                        "开始搜索$textStr",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            )
        )
        OutlinedTextField(
            value = textStr,
            onValueChange = {
                textStr = it
            },
            label = {
                Text(text = "输入提示1：")
            }
        )
        BasicTextField(value = textStr,
            onValueChange = {
                textStr = it
            },
            textStyle = TextStyle(color = Color.Red, fontWeight = FontWeight.Bold),
            maxLines = 2
        )
    }
}


@Composable
@Preview
fun EditTextLearnPre() {
    EditTextLearn()
}