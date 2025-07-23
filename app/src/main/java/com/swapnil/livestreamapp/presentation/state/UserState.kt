package com.swapnil.livestreamapp.presentation.state

import com.swapnil.livestreamapp.domain.model.User

data class UserState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String? = null
)
