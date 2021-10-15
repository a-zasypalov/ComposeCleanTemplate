package com.gaoyun.feature_home_screen.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.gaoyun.cct.common.LAUNCH_LISTEN_FOR_EFFECTS
import com.gaoyun.cct.domain.User
import com.gaoyun.cct.feature_user_details.USER_DETAILS_SCREEN_ROUTE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


@Composable
fun UsersScreenDestination(navHostController: NavHostController) {
    val viewModel: UsersViewModel = hiltViewModel()
    val state = viewModel.viewState.value
    UsersScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is UsersScreenContract.Effect.Navigation.ToUserDetails) {
                navHostController.navigate(USER_DETAILS_SCREEN_ROUTE)
            }
        }
    )
}

@Composable
fun UsersScreen(
    state: UsersScreenContract.State,
    effectFlow: Flow<UsersScreenContract.Effect>?,
    onEventSent: (event: UsersScreenContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: UsersScreenContract.Effect.Navigation) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is UsersScreenContract.Effect.DataWasLoaded -> {
                }
                is UsersScreenContract.Effect.Navigation.ToUserDetails -> onNavigationRequested(effect)
            }
        }?.collect()
    }

    Scaffold(scaffoldState = scaffoldState) {
        Box {
            UsersList(usersList = state.users) { userLogin ->
                onEventSent(UsersScreenContract.Event.UserSelection(userLogin))
            }
            if (state.isLoading) {
                Loader()
            }
        }
    }
}

@Composable
fun UsersList(
    usersList: List<User>,
    onItemClicked: (login: String) -> Unit = { }
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(usersList) { item -> UserItem(user = item, onItemClicked = onItemClicked) }
    }
}

@Composable
fun UserItem(
    user: User,
    onItemClicked: (login: String) -> Unit = { }
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clickable { onItemClicked(user.login) }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(
                    data = user.avatarUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                modifier = Modifier
                    .size(88.dp)
                    .padding(start = 8.dp, top = 16.dp, bottom = 16.dp),
                contentDescription = "User avatar",
            )

            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(user.login, fontSize = 18.sp)
                Text(user.type, fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun Loader() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}