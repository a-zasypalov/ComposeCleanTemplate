package com.gaoyun.cct.domain

data class User(
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val type: String,
    val isAdmin: Boolean
)