package com.afrosin.filmsearch.scheduler

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler


class DefaultSchedulers : Schedulers {
    override fun backgroundIo(): Scheduler = io.reactivex.rxjava3.schedulers.Schedulers.io()
    override fun backgroundNewThread(): Scheduler =
        io.reactivex.rxjava3.schedulers.Schedulers.newThread()

    override fun backgroundComputation(): Scheduler =
        io.reactivex.rxjava3.schedulers.Schedulers.computation()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}