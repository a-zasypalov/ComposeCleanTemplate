package com.gaoyun.cct.data.model

import com.gaoyun.cct.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("id")
    val id: Long,

    @SerialName("login")
    val login: String? = null,

    @SerialName("avatar_url")
    val avatarUrl: String? = null,

    @SerialName("type")
    val type: String? = null,

    @SerialName("site_admin")
    val isAdmin: Boolean? = null
)

fun UserResponse.mapToUser(): User = User(
    id = id,
    login = login ?: "",
    avatarUrl = avatarUrl ?: "",
    type = type ?: "",
    isAdmin = isAdmin ?: false
)