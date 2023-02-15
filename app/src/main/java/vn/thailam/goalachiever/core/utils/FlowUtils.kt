package vn.thailam.goalachiever.core.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

//fun <T> Flow<T>.toDataState(): Flow<DataState<T>> {
//    return this.onStart {
//        emit(DataState.Loading())
//    }
//        .map {
//        DataState.Success(it)
//    }
//}