package com.swapnil.livestreamapp.domain.use_case.Users

import com.swapnil.livestreamapp.domain.model.User
import com.swapnil.livestreamapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}
