package jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource

import io.reactivex.Observable
import jp.ne.keisuke.kiuchi.devicekanrisan.MainApplication
import jp.ne.keisuke.kiuchi.devicekanrisan.data.entity.UserEntity
import javax.inject.Inject

class UserRepository @Inject constructor() : UserDataSource {

    override fun getUserEntity(): Observable<UserEntity> {
        return Observable.create { e ->
            e.onNext(MainApplication.loginUser)
            e.onComplete()
        }
    }

    override fun login(userEntity: UserEntity): Observable<UserEntity> {
        return Observable.create { e ->
            MainApplication.loginUser = userEntity
            e.onNext(MainApplication.loginUser)
            e.onComplete()
        }
    }

    override fun logout(): Observable<UserEntity> {
        return Observable.create { e ->
            val oldUserEntity = MainApplication.loginUser.copy()
            MainApplication.loginUser = MainApplication.emptyUser
            e.onNext(oldUserEntity)
            e.onComplete()
        }
    }
}
