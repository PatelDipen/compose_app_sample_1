package com.compose_app_sample_1.utils

sealed class DomainResult<out T> {
    data class Success<T>(val data: T) : DomainResult<T>()
    data class Error(val failure: Failure) : DomainResult<Nothing>()
}