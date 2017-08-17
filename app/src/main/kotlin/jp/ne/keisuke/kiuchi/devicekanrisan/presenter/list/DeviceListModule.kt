package jp.ne.keisuke.kiuchi.devicekanrisan.presenter.list

import dagger.Binds
import dagger.Module

@Module
abstract class DeviceListModule {
    @Binds abstract fun bindView(fragment: DeviceListFragment): DeviceListContract.View
    @Binds abstract fun bindPresenter(presenter: DeviceListPresenter): DeviceListContract.Presenter
}
