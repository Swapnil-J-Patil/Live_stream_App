package com.swapnil.livestreamapp.domain.model

import com.swapnil.livestreamapp.data.remote.dto.UserDto

data class User(
    val fullName: String,
    val imageUrl: String
)
fun UserDto.toDomain(): User {
    return User(
        fullName = "${name.first} ${name.last}",
        imageUrl = picture.large
    )
}
