package com.analysis.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoAuthClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthClient
