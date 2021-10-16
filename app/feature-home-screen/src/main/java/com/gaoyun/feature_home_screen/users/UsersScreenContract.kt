package com.gaoyun.feature_home_screen.users

import com.gaoyun.cct.common.ViewEvent
import com.gaoyun.cct.common.ViewSideEffect
import com.gaoyun.cct.common.ViewState
import com.gaoyun.cct.domain.model.User

class UsersScreenContract {
    sealed class Event : ViewEvent {
        data class UserSelection(val userName: String) : Event()
    }

    data class State(val users: List<User> = listOf(), val isLoading: Boolean = false) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToUserDetails(val userName: String) : Navigation()
        }
    }
}