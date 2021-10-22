package com.gaoyun.feature_home_screen.users

import androidx.lifecycle.viewModelScope
import com.gaoyun.cct.common.BaseViewModel
import com.gaoyun.cct.domain.interactor.UserListInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userListInteractor: UserListInteractor
) : BaseViewModel<UsersScreenContract.Event, UsersScreenContract.State, UsersScreenContract.Effect>() {

    init {
        viewModelScope.launch { getUsersPage() }
    }

    override fun setInitialState() = UsersScreenContract.State(listOf(), true)

    override fun handleEvents(event: UsersScreenContract.Event) {
        when (event) {
            is UsersScreenContract.Event.UserSelection -> {
                setEffect { UsersScreenContract.Effect.Navigation.ToUserDetails(event.userName) }
            }
        }
    }

    private suspend fun getUsersPage() = userListInteractor.getUsersPage(50, 0)
        .flowOn(Dispatchers.IO)
        .catch { exception -> exception.printStackTrace() }
        .collect { users ->
            setState { copy(users = users, isLoading = false) }
        }

}