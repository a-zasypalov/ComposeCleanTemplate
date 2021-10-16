package com.gaoyun.feature_home_screen.repositories

import com.gaoyun.cct.common.ViewEvent
import com.gaoyun.cct.common.ViewSideEffect
import com.gaoyun.cct.common.ViewState
import com.gaoyun.cct.domain.model.Repository

class RepositoriesScreenContract {
    sealed class Event : ViewEvent {
        data class RepositorySelection(val owner: String, var repoName: String) : Event()
    }

    data class State(val repositories: List<Repository> = listOf(), val isLoading: Boolean = false) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToRepositoryDetails(val owner: String, var repoName: String) : Navigation()
        }
    }
}