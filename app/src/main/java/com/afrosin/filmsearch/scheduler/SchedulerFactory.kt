package com.afrosin.filmsearch.scheduler

object SchedulerFactory {
    fun create(): Schedulers = DefaultSchedulers()
}