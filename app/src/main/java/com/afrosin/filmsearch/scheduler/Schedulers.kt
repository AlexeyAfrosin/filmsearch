package com.afrosin.filmsearch.scheduler

import io.reactivex.rxjava3.core.Scheduler


interface Schedulers {
    fun backgroundIo(): Scheduler
    fun backgroundComputation(): Scheduler
    fun backgroundNewThread(): Scheduler
    fun main(): Scheduler
}