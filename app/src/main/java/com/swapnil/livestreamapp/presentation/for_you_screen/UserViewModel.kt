package com.swapnil.livestreamapp.presentation.for_you_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swapnil.livestreamapp.domain.use_case.Users.GetUsersUseCase
import com.swapnil.livestreamapp.presentation.state.UserIntent
import com.swapnil.livestreamapp.presentation.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserState())
    val state: StateFlow<UserState> = _state

    fun handleIntent(intent: UserIntent) {
        when (intent) {
            is UserIntent.LoadUsers -> loadUsers()
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val users = getUsersUseCase()
                _state.value = UserState(users = users)
            } catch (e: Exception) {
                _state.value = UserState(error = e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
