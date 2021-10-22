package com.gaoyun.cct.feature_user_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.gaoyun.cct.common.BaseViewModel
import com.gaoyun.cct.common.NavigationKeys
import com.gaoyun.cct.domain.interactor.UserDetailsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val userDetailsInteractor: UserDetailsInteractor
) : BaseViewModel<UserDetailsScreenContract.Event, UserDetailsScreenContract.State, UserDetailsScreenContract.Effect>() {

    init {
        viewModelScope.launch {
            val userLogin = stateHandle.get<String>(NavigationKeys.Arg.USER_ID)
                ?: throw IllegalStateException("No userLogin was passed to destination.")

            userDetailsInteractor.getUser(userLogin)
                .flowOn(Dispatchers.IO)
                .catch { exception -> exception.printStackTrace() }
                .collect { response ->
                    setState { copy(userDetails = response, isloading = false) }
                }
        }
    }

    override fun setInitialState() = UserDetailsScreenContract.State(null, true)

    override fun handleEvents(event: UserDetailsScreenContract.Event) {}


}