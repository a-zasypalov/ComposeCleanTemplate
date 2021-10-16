package com.gaoyun.cct.common

import com.gaoyun.cct.common.NavigationKeys.Arg.USER_ID

object NavigationKeys {

    object Arg {
        const val USER_ID = "USER_ID"
    }

    object Route {
        const val USERS_LIST = "USERS_LIST"
        const val REPOS_LIST = "REPOS_LIST"

        const val USER_DETAILS = "$USERS_LIST/{$USER_ID}"
    }

}