package com.gaoyun.cct.data.repository

import com.gaoyun.cct.data.model.mapToUser
import com.gaoyun.cct.data.model.mapToUserDetails
import com.gaoyun.cct.data.network.UserRoutes
import com.gaoyun.cct.domain.model.User
import com.gaoyun.cct.domain.model.UserDetails
import com.gaoyun.cct.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRoutes: UserRoutes
) : UserRepository {

    override suspend fun getUsersPage(pageSize: Int, since: Int): List<User> =
        userRoutes.getUsersPage(pageSize, since).map { it.mapToUser() }

    override suspend fun getUser(login: String): UserDetails =
        userRoutes.getUser(login).mapToUserDetails()

}