package com.gaoyun.cct.domain.model

data class Repository(
    val id: Long,
    val name: String,
    val fullName: String,
    val owner: User,
    val private: Boolean,
    val htmlURL: String,
    val description: String,
    val fork: Boolean,
)
