package jp.ne.keisuke.kiuchi.devicekanrisan.data.datasource

import io.reactivex.Observable
import jp.ne.keisuke.kiuchi.devicekanrisan.data.entity.UserEntity

interface UserDataSource {
    fun login(userEntity: UserEntity): Observable<UserEntity>
    fun logout(): Observable<UserEntity>
    fun getUserEntity(): Observable<UserEntity>
}
