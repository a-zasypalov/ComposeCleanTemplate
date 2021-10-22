package com.gaoyun.cct.common

import com.gaoyun.cct.common.NavigationKeys.Arg.REPO_ID
import com.gaoyun.cct.common.NavigationKeys.Arg.USER_ID

object NavigationKeys {

    object Arg {
        const val USER_ID = "USER_ID"
        const val REPO_ID = "REPO_ID"
    }

    object RouteLocal {
        const val USERS_LIST = "USERS_LIST"
        const val REPOS_LIST = "REPOS_LIST"

        const val USER_DETAILS = "$USERS_LIST/{$USER_ID}"
    }

    object RouteGlobal {
        const val HOME = "HOME"
        const val REPOS_LIST_GLOBAL = "REPOS_LIST_GLOBAL"
        const val REPOS_DETAILS_GLOBAL = "${REPOS_LIST_GLOBAL}/{$USER_ID}/{$REPO_ID}"
    }

}