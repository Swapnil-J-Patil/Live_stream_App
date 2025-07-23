package com.swapnil.livestreamapp.presentation.state

sealed class UserIntent {
    object LoadUsers : UserIntent()
}
