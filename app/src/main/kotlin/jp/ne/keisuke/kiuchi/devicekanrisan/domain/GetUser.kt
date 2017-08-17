package jp.ne.keisuke.kiuchi.devicekanrisan.domain

import io.reactivex.Observable
import jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource.UserDataSource
import jp.ne.keisuke.kiuchi.devicekanrisan.data.entity.UserEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.ExecutionThreads
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.OutputOnlyUseCase
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.UseCase
import javax.inject.Inject

class GetUser @Inject constructor(private val userDataSource: UserDataSource, executionThreads: ExecutionThreads)
    : OutputOnlyUseCase<GetUser.GetUserResponse>(executionThreads) {
    override fun execute(): Observable<GetUserResponse> {
        return userDataSource.getUserEntity().map { GetUserResponse(it) }
    }

    data class GetUserResponse(val userEntity: UserEntity) : UseCase.ResponseValue
}
