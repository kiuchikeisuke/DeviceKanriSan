package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.main

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent : AndroidInjector<MainFragment> {
    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<MainFragment>()
}
