package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.list

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(DeviceListModule::class))
interface DeviceListComponent : AndroidInjector<DeviceListFragment> {
    @Subcomponent.Builder abstract class Builder : AndroidInjector.Builder<DeviceListFragment>()
}
