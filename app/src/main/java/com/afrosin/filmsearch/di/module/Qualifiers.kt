package com.afrosin.filmsearch.di.module

import javax.inject.Qualifier

@Qualifier
annotation class Cloud

@Qualifier
annotation class Cache

@Qualifier
annotation class InMemory

@Qualifier
annotation class Persisted