package com.gaoyun.feature_home_screen.users

import androidx.lifecycle.viewModelScope
import com.gaoyun.cct.common.BaseViewModel
import com.gaoyun.cct.domain.interactor.UserListInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private suspend fun getUsersPage() {
        try {
            val users = userListInteractor.getUsersPage(50, 0)
            setState {
                copy(users = users, isLoading = false)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}