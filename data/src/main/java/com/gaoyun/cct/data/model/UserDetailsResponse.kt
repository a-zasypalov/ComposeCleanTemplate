package com.gaoyun.cct.data.model

import com.gaoyun.cct.domain.model.UserDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailsResponse(
    val id: Long,

    val login: String? = null,

    @SerialName("avatar_url")
    val avatarURL: String? = null,

    val url: String? = null,

    @SerialName("html_url")
    val htmlURL: String? = null,
    val type: String? = null,

    @SerialName("site_admin")
    val isAdmin: Boolean? = null,

    val name: String? = null,
    val company: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    val hireable: Boolean? = null,
    val bio: String? = null,

    @SerialName("twitter_username")
    val twitterUsername: String? = null,

    @SerialName("public_repos")
    val publicRepos: Int? = null,

    @SerialName("public_gists")
    val publicGists: Int? = null,

    val followers: Int? = null,
    val following: Int? = null,
)

fun UserDetailsResponse.mapToUserDetails(): UserDetails =
    UserDetails(
        id = this.id,
        login = this.login ?: "",
        avatarURL = this.avatarURL ?: "",
        url = this.url ?: "",
        htmlURL = this.htmlURL ?: "",
        type = this.type ?: "",
        isAdmin = this.isAdmin ?: false,
        name = this.name ?: "",
        company = this.company ?: "",
        blog = this.blog ?: "",
        location = this.location ?: "",
        email = this.email ?: "",
        hireable = this.hireable ?: false,
        bio = this.bio ?: "",
        twitterUsername = this.twitterUsername ?: "",
        publicRepos = this.publicRepos ?: 0,
        publicGists = this.publicGists ?: 0,
        followers = this.followers ?: 0,
        following = this.following ?: 0
    )
