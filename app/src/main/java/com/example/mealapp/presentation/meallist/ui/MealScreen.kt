package com.example.mealapp.presentation.meallist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mealapp.domain.resource.MealError
import com.example.mealapp.presentation.common.MealItem
import com.example.mealapp.presentation.meallist.MealViewModel
import com.example.mealapp.presentation.meallist.state.MealUiItem
import com.example.mealapp.presentation.theme.MealAppTheme
import com.example.mealapp.presentation.util.getMessage
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MealScreen(
    modifier: Modifier = Modifier,
    viewModel: MealViewModel = koinViewModel()
) {
    val mealListState by viewModel.mealUiState.collectAsStateWithLifecycle()
    MealUi(
        modifier = modifier,
        meals = mealListState.meals,
        isLoading = mealListState.isLoading,
        error = mealListState.error,
        onSearchQueryChange = { searchQuery ->
            viewModel.getMeals(searchQuery)
        }
    )

}

@Composable
fun MealUi(
    modifier: Modifier = Modifier,
    meals: List<MealUiItem>?,
    isLoading: Boolean,
    error: MealError?,
    onSearchQueryChange: (String) -> Unit,
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(searchQuery) {
        delay(300)
        if (searchQuery.length > 2)
            onSearchQueryChange(searchQuery)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text(text = "Search") },
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        )
        if (!meals.isNullOrEmpty()) {
            LazyColumn(
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .wrapContentSize()
            ) {
                items(meals, key = { meal -> meal.id }) { meal ->
                    MealItem(mealUiItem = meal, onItemClick = {

                    })
                }
            }
        } else {
            error?.getMessage()?.let { errorMessage ->
                Box(
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .wrapContentSize()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = errorMessage,
                        modifier = modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        if (isLoading) {
            Box(
                modifier = modifier
                    .wrapContentSize()
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealUiPreview() {
    MealAppTheme {
        MealUi(
            meals = listOf(
                MealUiItem(
                    id = "1",
                    name = "Meal 1",
                    imageUrl = "https://www.themealdb.com/images/media/meals/15.jpeg",
                    category = "Category 1",
                    area = "Area 1",
                    instructions = "Instructions for Meal 1",

                    ),
                MealUiItem(
                    id = "2",
                    name = "Meal 2",
                    imageUrl = "https://www.themealdb.com/images/media/meals/15.jpeg",
                    category = "Category 2",
                    area = "Area 2",
                    instructions = "Instructions for Meal 2"
                ),
                MealUiItem(
                    id = "3",
                    name = "Meal 3",
                    imageUrl = "https://www.themealdb.com/images/media/meals/15.jpeg",
                    category = "Category 3",
                    area = "Area 3",
                    instructions = "Instructions for Meal 3"
                )
            ),
            isLoading = false,
            error = null,
            onSearchQueryChange = {}
        )
    }
}


