package com.gaoyun.cct.domain.model

data class User(
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val type: String,
    val isAdmin: Boolean
)