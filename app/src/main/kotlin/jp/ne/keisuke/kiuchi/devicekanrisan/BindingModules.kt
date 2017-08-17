package jp.ne.keisuke.kiuchi.devicekanrisan

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import jp.ne.keisuke.kiuchi.devicekanrisan.presenter.list.DeviceListComponent
import jp.ne.keisuke.kiuchi.devicekanrisan.presenter.list.DeviceListFragment
import jp.ne.keisuke.kiuchi.devicekanrisan.presenter.login.LoginComponent
import jp.ne.keisuke.kiuchi.devicekanrisan.presenter.login.LoginFragment
import jp.ne.keisuke.kiuchi.devicekanrisan.presenter.main.MainComponent
import jp.ne.keisuke.kiuchi.devicekanrisan.presenter.main.MainFragment
import jp.ne.keisuke.kiuchi.devicekanrisan.presenter.reader.QRCodeReaderComponent
import jp.ne.keisuke.kiuchi.devicekanrisan.presenter.reader.QRCodeReaderFragment

/* bind modules for Presenter's modules */
@Module(subcomponents = arrayOf(
        QRCodeReaderComponent::class,
        MainComponent::class,
        DeviceListComponent::class,
        LoginComponent::class
))
abstract class BindingModules {
    @Binds
    @IntoMap
    @FragmentKey(LoginFragment::class) abstract fun bindLoginFragment(builder: LoginComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(QRCodeReaderFragment::class) abstract fun bindQRCodeReaderFragment(builder: QRCodeReaderComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(MainFragment::class) abstract fun bindMainFragment(builder: MainComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(DeviceListFragment::class) abstract fun bindDeviceListFragment(builder: DeviceListComponent.Builder): AndroidInjector.Factory<out Fragment>
}
