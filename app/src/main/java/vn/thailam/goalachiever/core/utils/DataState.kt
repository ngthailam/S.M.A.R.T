package vn.thailam.goalachiever.core.utils

sealed class DataState<out T> {
    object Loading : DataState<Any>()
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val t: Throwable) : DataState<Throwable>()
}

sealed class DataLoadState {
    object Loading: DataLoadState()
    object Success: DataLoadState()
    object Error: DataLoadState()
}