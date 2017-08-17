package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.login

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(LoginModule::class))
interface LoginComponent : AndroidInjector<LoginFragment> {
    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<LoginFragment>()
}
