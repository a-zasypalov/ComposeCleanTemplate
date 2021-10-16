package com.gaoyun.cct.data.model

import com.gaoyun.cct.domain.model.Repository
import com.gaoyun.cct.domain.model.User
import kotlinx.serialization.SerialName

data class RepositoryResponse(
    val id: Long? = null,

    val name: String? = null,

    @SerialName("full_name")
    val fullName: String? = null,

    val owner: UserResponse? = null,
    
    val private: Boolean? = null,

    @SerialName("html_url")
    val htmlURL: String? = null,

    val description: String? = null,
    val fork: Boolean? = null,
)

fun RepositoryResponse.mapToRepository() = Repository(
    id = this.id ?: 0,
    name = this.name ?: "",
    fullName = this.fullName ?: "",
    owner = this.owner?.mapToUser() ?: User(0, "", "", "", false),
    private = this.private ?: false,
    htmlURL = this.htmlURL ?: "",
    description = this.description ?: "",
    fork = this.fork ?: false,
)
