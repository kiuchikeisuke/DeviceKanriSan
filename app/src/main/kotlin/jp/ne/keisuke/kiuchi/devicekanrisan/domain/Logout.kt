package jp.ne.keisuke.kiuchi.devicekanrisan.domain

import io.reactivex.Observable
import jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource.UserDataSource
import jp.ne.keisuke.kiuchi.devicekanrisan.data.entity.UserEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.ExecutionThreads
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.OutputOnlyUseCase
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.UseCase
import javax.inject.Inject

class Logout @Inject constructor(private val userDataSource: UserDataSource, executionThreads: ExecutionThreads)
    : OutputOnlyUseCase<Logout.LogoutResponse>(executionThreads) {
    override fun execute(): Observable<LogoutResponse> {
        return userDataSource.logout().map { LogoutResponse(it) }
    }

    data class LogoutResponse(val userEntity: UserEntity) : UseCase.ResponseValue
}
