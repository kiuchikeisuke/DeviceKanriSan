package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.login

import dagger.Binds
import dagger.Module

@Module
abstract class LoginModule {
    @Binds abstract fun bindView(fragment: LoginFragment): LoginContract.View
    @Binds abstract fun bindPresenter(presenter: LoginPresenter): LoginContract.Presenter
}
