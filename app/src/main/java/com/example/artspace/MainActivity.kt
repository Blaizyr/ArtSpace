package com.example.artspace

import android.icu.number.Scale
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {}
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(
    modifier: Modifier = Modifier,
) {
    var artId by remember { mutableStateOf(1) }

    Column(
        modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ImageSection(
            artId,
            modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )
        Spacer(modifier = Modifier.height(10.dp))

        DescriptionSection(
            artId,
            onIdChange = { },
            modifier = modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)

        )
        Spacer(modifier = Modifier.height(5.dp))

        ControlSection(
            artId = artId,
            onIdChange = { artId = it },
            modifier = modifier
                //.fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )

        Text(text = artId.toString())
        Text(text = descrSectionContent[0].size.toString())
    }

}

@Composable
fun ImageSection(
    artId: Int,
    modifier: Modifier = Modifier,
) {
    var imageId = when (artId) {
        1 -> R.drawable.ic_launcher_foreground
        2 -> R.drawable.ic_launcher_background
        else -> androidx.core.R.drawable.notify_panel_notification_icon_bg
    }
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
    )
}

val descrSectionContent = arrayOf(
    arrayOf("aaaa", "bbbb", "cccc"),
    arrayOf("XXXX", "YYYY", "ZZZZ")
)

@Composable
fun DescriptionSection(
    artId: Int,
    onIdChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {


    var titleId = ""
    var descrId = ""

    when (artId) {
        1 -> {
            titleId = descrSectionContent[0][0]
            descrId = descrSectionContent[1][0]
        }
        2 -> {
            titleId = descrSectionContent[0][1]
            descrId = descrSectionContent[1][1]
        }
        3 -> {
            titleId = descrSectionContent[0][2]
            descrId = descrSectionContent[1][2]
        }

    }

    Column(
    ) {
        Text(
            text = titleId
        )
        Text(
            text = descrId
        )
    }
}

@Composable
fun ControlSection(
    artId: Int,
    onIdChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
    ) {

        Button( //Previous
            onClick = {
                if (artId == 1) {
                    onIdChange(artId + (descrSectionContent[0].size - 1))
                } else {
                    onIdChange(artId - 1)
                }
            },
            Modifier
                .weight(0.2f)
                .padding(end = 8.dp, start = 15.dp)
        ) {
            Text(
                text = "Previous"
            )
        }

        Button( //Next
            onClick = {
                if (artId == (descrSectionContent[0].size)) {
                    onIdChange(artId - (descrSectionContent[0].size - 1))
                } else {
                    onIdChange(artId + 1)
                }
            },
            Modifier
                .weight(0.2f)
                .padding(start = 8.dp, end = 15.dp)
        ) {
            Text(
                text = "Next"
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        )
    }
}