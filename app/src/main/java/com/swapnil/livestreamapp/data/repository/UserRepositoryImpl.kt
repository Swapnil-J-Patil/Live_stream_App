package com.swapnil.livestreamapp.data.repository

import com.swapnil.livestreamapp.data.remote.UserApi
import com.swapnil.livestreamapp.domain.model.User
import com.swapnil.livestreamapp.domain.model.toDomain
import com.swapnil.livestreamapp.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {
    override suspend fun getUsers(): List<User> {
        return api.getUsers().results.map { it.toDomain() }
    }
}
