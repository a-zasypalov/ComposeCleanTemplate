package com.gaoyun.feature_home_screen.repositories

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.gaoyun.cct.common.LAUNCH_LISTEN_FOR_EFFECTS
import com.gaoyun.cct.common.NavigationKeys
import com.gaoyun.cct.domain.model.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun RepositoriesScreenDestination(navHostController: NavHostController, globalNavController: NavController) {
    val viewModel: RepositoriesListViewModel = hiltViewModel()
    val state = viewModel.viewState.value

    ReositoriesScreen(
        state = state,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is RepositoriesScreenContract.Effect.Navigation.ToRepositoryDetails) {
                globalNavController.navigate("${NavigationKeys.RouteGlobal.REPOS_LIST_GLOBAL}/${navigationEffect.owner}/${navigationEffect.repoName}")
            }
        }
    )
}

@Composable
fun ReositoriesScreen(
    state: RepositoriesScreenContract.State,
    effectFlow: Flow<RepositoriesScreenContract.Effect>?,
    onEventSent: (event: RepositoriesScreenContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: RepositoriesScreenContract.Effect.Navigation) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is RepositoriesScreenContract.Effect.DataWasLoaded -> {
                }
                is RepositoriesScreenContract.Effect.Navigation.ToRepositoryDetails -> onNavigationRequested(effect)
            }
        }?.collect()
    }

    Scaffold(scaffoldState = scaffoldState) {
        Box {
            RepositoriesList(repositoriesList = state.repositories) { owner, repoName ->
                onEventSent(RepositoriesScreenContract.Event.RepositorySelection(owner, repoName))
            }
            if (state.isLoading) {
                com.gaoyun.feature_home_screen.users.Loader()
            }
        }
    }
}

@Composable
fun RepositoriesList(
    repositoriesList: List<Repository>,
    onItemClicked: (owner: String, repoName: String) -> Unit = { _, _ -> }
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(repositoriesList) { item -> RepositoryItem(repository = item, onItemClicked = onItemClicked) }
    }
}

@Composable
fun RepositoryItem(
    repository: Repository,
    onItemClicked: (owner: String, repoName: String) -> Unit = { _, _ -> }
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clickable { onItemClicked(repository.owner.login, repository.name) }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(
                    data = repository.owner.avatarUrl,
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
                Text(repository.name, fontSize = 18.sp)
                Text("By: ${repository.owner.login}", fontSize = 14.sp)
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