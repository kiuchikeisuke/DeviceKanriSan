package jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons

import io.reactivex.Scheduler

interface ExecutionThreads {
    fun io(): Scheduler
    fun ui(): Scheduler
}
