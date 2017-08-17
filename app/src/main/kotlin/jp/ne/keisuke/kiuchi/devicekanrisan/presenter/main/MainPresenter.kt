package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.main

import jp.ne.keisuke.kiuchi.devicekanrisan.domain.GetUser
import jp.ne.keisuke.kiuchi.devicekanrisan.domain.Logout
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.DefaultDisposableObserver
import jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons.UseCase
import javax.inject.Inject

class MainPresenter @Inject constructor(
        private val getUser: GetUser,
        private val logout: Logout,
        private val view: MainContract.View) : MainContract.Presenter {
    override fun load() {
        getUser.execute(UseCase.NoRequestValue.INSTANCE, DefaultDisposableObserver<GetUser.GetUserResponse>(next = { view.loaded(it.userEntity) }))
    }

    override fun logout() {
        logout.execute(UseCase.NoRequestValue.INSTANCE, DefaultDisposableObserver<Logout.LogoutResponse>(next = { view.logouted(it.userEntity) }))
    }

    override fun dispose() {
        getUser.dispose()
        logout.dispose()
        //TODO dispose Domain here!!
    }
}
