package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.login

import javax.inject.Inject

class LoginPresenter @Inject constructor(private val view: LoginContract.View) : LoginContract.Presenter {
    override fun dispose() {
        //TODO dispose Domain here!!
    }
}
