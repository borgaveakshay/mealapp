package com.example.mealapp.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mealapp.presentation.meallist.state.MealUiItem
import com.example.mealapp.presentation.theme.MealAppTheme

@Composable
fun MealItem(
    modifier: Modifier = Modifier,
    mealUiItem: MealUiItem,
    onItemClick: (id: String) -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 8.dp))
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .wrapContentSize()
            .clickable { onItemClick(mealUiItem.id) }) {

        Column(
            modifier = modifier
                .wrapContentSize()
        ) {
            AsyncImage(
                modifier = modifier
                    .fillMaxWidth()
                    .size(150.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp)),
                model = mealUiItem.imageUrl,
                contentDescription = "Meal Image",
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = modifier.heightIn(5.dp))
            Text(
                text = mealUiItem.name,
                style = MaterialTheme.typography.titleSmall,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.heightIn(5.dp))
            Text(
                text = mealUiItem.category,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = modifier.heightIn(5.dp))
            Text(
                text = mealUiItem.area,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = modifier.heightIn(5.dp))
            Text(
                text = mealUiItem.instructions,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun MealItemPreview() {
    MealAppTheme {
        MealItem(
            mealUiItem = MealUiItem(
                id = "1",
                name = "Pizza",
                imageUrl = "https://www.themealdb.com/images/media/meals/1548772327.jpg",
                category = "Category",
                area = "Area",
                instructions = "This is a description of the meal, which is a description of the meal, which is a description of the meal, which is a description of the meal, which is a description of the meal, which is a description of the meal."
            ),
            onItemClick = {}
        )
    }
}