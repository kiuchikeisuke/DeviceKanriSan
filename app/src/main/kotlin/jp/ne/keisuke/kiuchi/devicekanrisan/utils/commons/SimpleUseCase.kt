package jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons

import io.reactivex.Observable

abstract class SimpleUseCase(executionThreads: ExecutionThreads) : IoUseCase<UseCase.NoRequestValue, UseCase.NoResponseValue>(executionThreads) {
    protected abstract fun execute(): Observable<NoResponseValue>
    override fun execute(requestValue: NoRequestValue): Observable<NoResponseValue> = execute()
}
