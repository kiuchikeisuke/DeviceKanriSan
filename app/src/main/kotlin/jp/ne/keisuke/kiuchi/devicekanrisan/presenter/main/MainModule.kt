package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.main

import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {
    @Binds abstract fun bindView(fragment: MainFragment): MainContract.View
    @Binds abstract fun bindPresenter(presenter: MainPresenter): MainContract.Presenter
}
