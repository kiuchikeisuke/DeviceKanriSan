package jp.ne.keisuke.kiuchi.devicekanrisan.domain

import io.reactivex.Observable
import jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource.UserDataSource
import jp.ne.keisuke.kiuchi.devicekanrisan.data.entity.UserEntity
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.ExecutionThreads
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.IoUseCase
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.UseCase
import javax.inject.Inject

class Login @Inject constructor(private val userDataSource: UserDataSource, executionThreads: ExecutionThreads)
    : IoUseCase<Login.LoginRequest, Login.LoginResponse>(executionThreads) {
    override fun execute(requestValue: LoginRequest): Observable<LoginResponse> {
        return userDataSource.login(UserEntity(requestValue.userName, requestValue.mailAddress)).map { LoginResponse(it) }
    }

    data class LoginRequest(val userName: String, val mailAddress: String) : UseCase.RequestValue
    data class LoginResponse(val userEntity: UserEntity) : UseCase.ResponseValue
}
