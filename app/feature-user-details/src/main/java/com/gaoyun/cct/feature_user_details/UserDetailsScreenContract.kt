package com.gaoyun.cct.feature_user_details

import com.gaoyun.cct.common.ViewEvent
import com.gaoyun.cct.common.ViewSideEffect
import com.gaoyun.cct.common.ViewState
import com.gaoyun.cct.domain.model.UserDetails

class UserDetailsScreenContract {
    sealed class Event : ViewEvent

    data class State(val userDetails: UserDetails?, val isloading: Boolean = false) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {

        }
    }

}