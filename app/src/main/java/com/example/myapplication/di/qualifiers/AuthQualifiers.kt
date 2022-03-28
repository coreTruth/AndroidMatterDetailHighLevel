package com.example.myapplication.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class WithAuth

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NoAuth
