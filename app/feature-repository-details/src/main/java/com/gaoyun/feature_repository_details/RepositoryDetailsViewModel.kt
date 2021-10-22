package com.gaoyun.feature_repository_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.gaoyun.cct.common.BaseViewModel
import com.gaoyun.cct.common.NavigationKeys
import com.gaoyun.cct.domain.interactor.UserDetailsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailsViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val userDetailsInteractor: UserDetailsInteractor
) : BaseViewModel<RepositoryDetailsScreenContract.Event, RepositoryDetailsScreenContract.State, RepositoryDetailsScreenContract.Effect>() {

    init {
        viewModelScope.launch {
            val repositoryId = stateHandle.get<String>(NavigationKeys.Arg.REPO_ID)
                ?: throw IllegalStateException("No userLogin was passed to destination.")

            try {
                val response = userDetailsInteractor.getUser(repositoryId)
                setState {
                    copy(repositoryDetails = response, isloading = false)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun setInitialState() = RepositoryDetailsScreenContract.State(null, true)

    override fun handleEvents(event: RepositoryDetailsScreenContract.Event) {}


}