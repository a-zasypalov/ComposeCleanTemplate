package com.gaoyun.cct.domain.repository

import com.gaoyun.cct.domain.User

interface UserRepository {
    suspend fun getUsersPage(pageSize: Int, since: Int): List<User>
    suspend fun getUser(login: String): User
}