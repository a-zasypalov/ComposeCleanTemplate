package com.gaoyun.feature_repository_details

import com.gaoyun.cct.common.ViewEvent
import com.gaoyun.cct.common.ViewSideEffect
import com.gaoyun.cct.common.ViewState
import com.gaoyun.cct.domain.model.Repository
import com.gaoyun.cct.domain.model.UserDetails

class RepositoryDetailsScreenContract {
    sealed class Event : ViewEvent

    data class State(val repositoryDetails: Repository?, val isloading: Boolean = false) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {

        }
    }

}