package com.gotneb.honki.presentation.screen.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gotneb.honki.presentation.screen.home_screen.component.BookCard

@Composable
fun HomeScreen(
    state: HomeScreenState,
    onBookClick: (String?) -> Unit,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                value = state.search,
                onValueChange = onValueChange,
                placeholder = {
                    Text("Search...")
                },
                singleLine = true,
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                onSearchClick()
                            }
                    )
                },
                shape = RoundedCornerShape(12),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            )

            if (state.isLoading) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp)
                    )
                }
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (state.books.isNotEmpty()) {
                    items(
                        state.books,
                        key = { it.id }
                    ) { book ->
                        BookCard(
                            book = book,
                            modifier = Modifier.clickable { onBookClick(book.formats.html) }
                        )
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun SearchScreenPreview() {
//    HonkiBookReaderTheme {
//        SearchScreen()
//    }
//}