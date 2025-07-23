package com.swapnil.livestreamapp.data.remote

import com.swapnil.livestreamapp.data.remote.dto.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("api/")
    suspend fun getUsers(
        @Query("results") count: Int = 100
    ): UserResponseDto
}
