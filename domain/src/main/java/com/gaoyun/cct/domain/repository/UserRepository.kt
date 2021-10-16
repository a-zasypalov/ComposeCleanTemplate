package com.gaoyun.cct.domain.repository

import com.gaoyun.cct.domain.model.User
import com.gaoyun.cct.domain.model.UserDetails

interface UserRepository {
    suspend fun getUsersPage(pageSize: Int, since: Int): List<User>
    suspend fun getUser(login: String): UserDetails
}