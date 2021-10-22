package com.gaoyun.feature_repository_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gaoyun.cct.common.LAUNCH_LISTEN_FOR_EFFECTS
import com.gaoyun.cct.domain.model.Repository
import com.gaoyun.cct.domain.model.UserDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


@Composable
fun RepositoryDetailsDestination(globalNavHostController: NavController) {
    val viewModel = hiltViewModel<RepositoryDetailsViewModel>()
    val state = viewModel.viewState.value

    RepositoryDetailsScreen(state, viewModel.effect)
}

@Composable
fun RepositoryDetailsScreen(
    state: RepositoryDetailsScreenContract.State,
    effectFlow: Flow<RepositoryDetailsScreenContract.Effect>?,
) {
    LaunchedEffect(LAUNCH_LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is RepositoryDetailsScreenContract.Effect.DataWasLoaded -> {
                }
            }
        }?.collect()
    }

    Surface(color = MaterialTheme.colors.background) {
        if (state.isloading) {
            Loader()
        } else {
            state.repositoryDetails?.let { RepositoryDetails(it) } ?: ErrorText()
        }
    }
}

@Composable
fun RepositoryDetails(repo: Repository) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column {
            Text("Repo name: ${repo.name}", fontSize = 28.sp)
            Text("Author: ${repo.owner.login}", fontSize = 28.sp)
        }
    }
}

@Composable
fun Loader() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorText() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text("Something went wrong :(", fontSize = 24.sp)
    }
}