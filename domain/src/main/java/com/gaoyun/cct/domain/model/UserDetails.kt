package com.gaoyun.cct.domain.model

data class UserDetails(
    val id: Long,
    val login: String,
    val avatarURL: String,
    val url: String,
    val htmlURL: String,
    val type: String,
    val isAdmin: Boolean? = false,
    val name: String,
    val company: String,
    val blog: String,
    val location: String,
    val email: String,
    val hireable: Boolean? = false,
    val bio: String,
    val twitterUsername: String,
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int,
)
