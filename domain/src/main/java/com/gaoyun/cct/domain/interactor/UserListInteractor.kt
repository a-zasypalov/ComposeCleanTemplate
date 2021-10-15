package com.gaoyun.cct.domain.interactor

import com.gaoyun.cct.domain.repository.UserRepository
import javax.inject.Inject

class UserListInteractor @Inject constructor(
    private val repository: UserRepository
) {

    suspend fun getUsersPage(pageSize: Int, since: Int) = repository.getUsersPage(pageSize, since)

}