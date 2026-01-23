package com.compose_app_sample_1.utils

sealed class Failure {
    object Network : Failure()
    object NotFound : Failure()
    data class Unknown(val message: String) : Failure()
}