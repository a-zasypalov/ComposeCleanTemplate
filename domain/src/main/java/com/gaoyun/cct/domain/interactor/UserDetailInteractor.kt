package com.gaoyun.cct.domain.interactor

import com.gaoyun.cct.domain.repository.UserRepository
import javax.inject.Inject

class UserDetailInteractor @Inject constructor(
    private val repository: UserRepository
) {

    suspend fun getUser(login: String) = repository.getUser(login)

}