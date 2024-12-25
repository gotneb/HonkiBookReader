package com.gotneb.honki.presentation.screen.reader_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReaderScreen(
    state: ReaderScreenState,
) {
    Scaffold { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(20.dp)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp)
                )
            } else {
                LazyColumn {
                    items(state.content) {
                        Text(
                            text = it.text,
                            style = getTextStyleForTag(it.tag),
                        )
                    }
                }
            }
        }
    }
}

private fun getTextStyleForTag(tag: String): TextStyle {
    return when (tag) {
        "h1" -> TextStyle(fontSize = 28.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        "h2" -> TextStyle(fontSize = 22.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)
        "p" -> TextStyle(fontSize = 16.sp, color = Color.DarkGray)
        else -> TextStyle(fontSize = 14.sp, color = Color.DarkGray)
    }
}