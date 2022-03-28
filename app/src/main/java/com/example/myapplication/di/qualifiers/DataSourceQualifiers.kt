package com.example.myapplication.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RemoteBusinessDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class LocalBusinessDataSource
