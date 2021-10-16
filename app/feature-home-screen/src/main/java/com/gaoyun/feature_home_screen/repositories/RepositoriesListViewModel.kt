package com.gaoyun.feature_home_screen.repositories

import androidx.lifecycle.viewModelScope
import com.gaoyun.cct.common.BaseViewModel
import com.gaoyun.cct.domain.interactor.RepositoriesListInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesListViewModel @Inject constructor(
    private val repositoriesListInteractor: RepositoriesListInteractor
) : BaseViewModel<RepositoriesScreenContract.Event, RepositoriesScreenContract.State, RepositoriesScreenContract.Effect>() {

    init {
        viewModelScope.launch { getRepositoriesPage() }
    }

    override fun setInitialState() = RepositoriesScreenContract.State(listOf(), true)

    override fun handleEvents(event: RepositoriesScreenContract.Event) {
        when (event) {
            is RepositoriesScreenContract.Event.RepositorySelection -> {
                setEffect { RepositoriesScreenContract.Effect.Navigation.ToRepositoryDetails(event.owner, event.repoName) }
            }
        }
    }

    private suspend fun getRepositoriesPage() {
        try {
            val repositories = repositoriesListInteractor.getRepositoriesPage(0)
            setState {
                copy(repositories = repositories, isLoading = false)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}