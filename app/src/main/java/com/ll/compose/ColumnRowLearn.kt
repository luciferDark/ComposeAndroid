import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ll.compose.R

@Composable
fun ColumnRowLearn() {
    val aligns = arrayListOf<Alignment>(
        Alignment.TopStart, Alignment.TopCenter, Alignment.TopEnd,
        Alignment.CenterStart, Alignment.Center, Alignment.CenterEnd,
        Alignment.BottomStart, Alignment.BottomCenter, Alignment.BottomEnd
    )
    val columNum = 15
    val rowNum = 20
    val boxShape = RoundedCornerShape(60.dp, 0.dp, 25.dp, 0.dp)
    val boxShape2 = RoundedCornerShape(25.dp)
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(PaddingValues(8.dp, 10.dp))
            .height(250.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        repeat(columNum) {
            Box(
                contentAlignment = aligns[it % aligns.size],
                modifier = Modifier
                    .background(color = Color.Red, shape = boxShape)
                    .height(100.dp)
                    .fillMaxWidth()
                    .clip(boxShape)
            ) {
                Text(text = "100", modifier = Modifier.padding(10.dp))
            }
        }
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),//间隙
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(PaddingValues(8.dp, 10.dp))
            .fillMaxSize()
            .horizontalScroll(state = rememberScrollState())//水平滚动
    ) {
        Image(
            painter = painterResource(id = R.drawable.test),
            contentDescription = "",
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(15.dp, 0.dp, 25.dp, 0.dp)),//圆角
            contentScale = ContentScale.FillHeight
        )
        repeat(rowNum) {
            Box(
                contentAlignment = aligns[it % aligns.size],
                modifier = Modifier
                    .background(color = Color.Red, shape = boxShape2)
                    .size(100.dp, 200.dp)
                    .clip(shape = boxShape2)
            ) {
                Text(text = "$it", modifier = Modifier.padding(20.dp))
            }
        }
    }
}

@Preview
@Composable
fun ColumnRowLearnPre() {
    ColumnRowLearn()
}