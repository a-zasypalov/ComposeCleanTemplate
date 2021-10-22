package com.gaoyun.cct.domain.interactor

import com.gaoyun.cct.domain.repository.RepositoriesRepository
import com.gaoyun.cct.domain.repository.UserRepository
import javax.inject.Inject

class RepositoryDetailsInteractor @Inject constructor(
    private val repository: RepositoriesRepository
) {

    suspend fun getRepository(owner: String, name: String) = repository.getRepository(owner, name)

}