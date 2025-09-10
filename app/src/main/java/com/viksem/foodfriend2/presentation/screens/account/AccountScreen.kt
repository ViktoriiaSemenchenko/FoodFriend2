package com.viksem.foodfriend2.presentation.screens.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.viksem.foodfriend2.R
import com.viksem.foodfriend2.ui.theme.Gray300
import com.viksem.foodfriend2.ui.theme.Gray900

@Preview
@Composable
fun AccountScreen(navController: NavController? = null) {
    Scaffold(
        Modifier.padding(horizontal = 16.dp)
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding.calculateBottomPadding())) {
            AccountTopBar("Account", {})
            PersonalInfo()
            RecipeTabs()
        }
    }
}

@Composable
fun AccountTopBar(
    title: String,
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily(Font(R.font.cormorant_garamond)),
            fontWeight = FontWeight(600),
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = "Налаштування",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun PersonalInfo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                "Viktoriia Semenchenko",
                fontFamily = FontFamily(Font(R.font.mulish)),
                fontWeight = FontWeight(400),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                "vickysemenchenko@gmail.com",
                fontFamily = FontFamily(Font(R.font.mulish)),
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

        }
    }
}

@Composable
fun RecipeTabs() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Favorites", "My recepies")

    Column(Modifier.padding(top = 40.dp),) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = Gray900,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 3.dp,
                    color = Gray900
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (selectedTabIndex == index) {
                                Gray900
                            } else {
                                Gray300
                            }
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        when (selectedTabIndex) {
            0 -> ListContent()
            1 -> CalendarContent()
        }
    }
}

@Composable
fun ListContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Тут список улюблених рецептів")
    }
}

@Composable
fun CalendarContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Тут список створених рецептів")
    }
}
