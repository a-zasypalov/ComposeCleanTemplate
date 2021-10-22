package com.gaoyun.cct.domain.repository

import com.gaoyun.cct.domain.model.User
import com.gaoyun.cct.domain.model.UserDetails
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUsersPage(pageSize: Int, since: Int): Flow<List<User>>
    suspend fun getUser(login: String): Flow<UserDetails>
}