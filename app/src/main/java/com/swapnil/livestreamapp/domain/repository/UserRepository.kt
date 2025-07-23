package com.swapnil.livestreamapp.domain.repository

import com.swapnil.livestreamapp.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}
